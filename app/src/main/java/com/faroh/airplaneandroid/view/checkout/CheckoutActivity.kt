package com.faroh.airplaneandroid.view.checkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.faroh.airplaneandroid.R
import com.faroh.airplaneandroid.databinding.ActivityCheckoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutActivity : AppCompatActivity() {

    private lateinit var checkoutBinding: ActivityCheckoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkoutBinding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(checkoutBinding.root)


    }
}