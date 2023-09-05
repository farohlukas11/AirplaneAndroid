package com.faroh.airplaneandroid.view.checkout

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.faroh.airplaneandroid.core.domain.model.DestinationModel
import com.faroh.airplaneandroid.core.utils.Formatter
import com.faroh.airplaneandroid.databinding.ActivityCheckoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutActivity : AppCompatActivity() {

    private lateinit var checkoutBinding: ActivityCheckoutBinding
    private var dataDestination: DestinationModel? = null
    private var seatDestination: String? = null

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
        }

        seatDestination?.let {
            checkoutBinding.tvSeat.text = it
        }
    }

    companion object {
        const val DATA_ITEM = "data_item"
        const val DATA_SEAT = "seat_item"
    }
}