package com.faroh.airplaneandroid.view.chooseseat

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.faroh.airplaneandroid.R
import com.faroh.airplaneandroid.core.domain.model.DestinationModel
import com.faroh.airplaneandroid.core.utils.Formatter
import com.faroh.airplaneandroid.databinding.ActivityChooseSeatBinding
import com.faroh.airplaneandroid.view.checkout.CheckoutActivity
import com.faroh.airplaneandroid.view.detail.DetailActivity

class ChooseSeatActivity : AppCompatActivity() {
    private lateinit var chooseSeatBinding: ActivityChooseSeatBinding
    private var dataDestinationSeat: DestinationModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chooseSeatBinding = ActivityChooseSeatBinding.inflate(layoutInflater)
        setContentView(chooseSeatBinding.root)

        dataDestinationSeat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(DATA_SEAT, DestinationModel::class.java)
        } else {
            intent.getParcelableExtra(DATA_SEAT)
        }

        val dataString = "A3, B3"

        dataDestinationSeat?.let { item ->
            chooseSeatBinding.apply {
                tvSeat.text = dataString
                tvTotalPrice.text = Formatter.rupiahFormatter(item.price!! * 2)
            }

            chooseSeatBinding.btnContinueCheck.setOnClickListener {
                val intentCheck = Intent(this, CheckoutActivity::class.java)
                intentCheck.putExtra(CheckoutActivity.DATA_ITEM, item)
                intentCheck.putExtra(CheckoutActivity.DATA_SEAT, dataString)
                startActivity(intentCheck)
            }
        }
    }

    companion object {
        const val DATA_SEAT = "data_seat"
    }
}