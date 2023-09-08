package com.faroh.airplaneandroid.view.checkout

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.faroh.airplaneandroid.MainActivity
import com.faroh.airplaneandroid.core.data.Resource
import com.faroh.airplaneandroid.core.domain.model.TransactionModel
import com.faroh.airplaneandroid.core.domain.model.DestinationModel
import com.faroh.airplaneandroid.core.utils.Formatter
import com.faroh.airplaneandroid.core.utils.ToastUtils.showCustomToast
import com.faroh.airplaneandroid.databinding.ActivityCheckoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutActivity : AppCompatActivity() {

    private lateinit var checkoutBinding: ActivityCheckoutBinding
    private var dataDestination: DestinationModel? = null
    private var seatDestination: String? = null
    private val checkoutViewModel: CheckoutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkoutBinding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(checkoutBinding.root)

        dataDestination = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(DATA_ITEM, DestinationModel::class.java)
        } else {
            intent.getParcelableExtra(DATA_ITEM)
        }
        seatDestination = intent.getStringExtra(DATA_SEAT)

        dataDestination?.let { item ->
            val price = item.price!! * 2
            val grandTotal = (price * 0.45) + price
            checkoutBinding.apply {
                Glide.with(this@CheckoutActivity).load(item.imageUrl)
                    .transform(CenterCrop(), RoundedCorners(18))
                    .into(ivDestinationBook)
                tvNameBook.text = item.name
                tvCityBook.text = item.city
                tvRatingBook.text = item.rating.toString()
                tvPriceBook.text = Formatter.rupiahFormatter(price)
                tvGrandTotal.text = Formatter.rupiahFormatter(grandTotal)
            }

            seatDestination?.let { seat ->
                checkoutBinding.tvSeat.text = seat
            }

            checkoutViewModel.getUserToken().observe(this) { token ->
                token?.let {
                    checkoutViewModel.getUserById(it).observe(this) { response ->
                        when (response) {
                            is Resource.Loading -> {}
                            is Resource.Success -> {
                                val user = response.data
                                checkoutBinding.tvPricePay.text =
                                    Formatter.rupiahFormatter(user?.balance)

                                checkoutBinding.btnPayNow.setOnClickListener { _ ->

                                    if (user?.balance!! < grandTotal) {
                                        Toast(
                                            this@CheckoutActivity
                                        ).showCustomToast(
                                            false,
                                            "Don't Have Checkout ${item.name}",
                                            this@CheckoutActivity
                                        )
                                    } else {
                                        val userBalance = user.balance - grandTotal

                                        checkoutViewModel
                                            .checkoutDestination(
                                                TransactionModel(
                                                    amountOrTraveler = 2,
                                                    destination = item,
                                                    grandTotal = grandTotal,
                                                    insurance = true,
                                                    price = price,
                                                    refundable = false,
                                                    selectedSeats = seatDestination,
                                                    vat = 0.45
                                                )
                                            )

                                        checkoutViewModel.updateUserBalance(it, userBalance)

                                        Toast(
                                            this@CheckoutActivity
                                        ).showCustomToast(
                                            false,
                                            "Success Checkout ${item.name}",
                                            this@CheckoutActivity
                                        )
                                        val i = Intent(
                                            this@CheckoutActivity,
                                            MainActivity::class.java
                                        )
                                        i.flags =
                                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                        startActivity(
                                            i
                                        )
                                    }
                                }
                            }

                            is Resource.Error -> {}
                        }
                    }
                }
            }
        }
    }


    companion object {
        const val DATA_ITEM = "data_item"
        const val DATA_SEAT = "seat_item"
    }
}

