package com.faroh.airplaneandroid.view.bonus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.faroh.airplaneandroid.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BonusSaldoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bonus_saldo)
    }
}