package com.faroh.airplaneandroid.view.bonus

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.faroh.airplaneandroid.MainActivity
import com.faroh.airplaneandroid.R
import com.faroh.airplaneandroid.core.data.Resource
import com.faroh.airplaneandroid.core.utils.Formatter
import com.faroh.airplaneandroid.core.utils.ToastUtils.showCustomToast
import com.faroh.airplaneandroid.databinding.ActivityBonusSaldoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BonusSaldoActivity : AppCompatActivity() {

    private lateinit var bonusSaldoBinding: ActivityBonusSaldoBinding
    private val bonusSaldoViewModel: BonusSaldoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bonusSaldoBinding = ActivityBonusSaldoBinding.inflate(layoutInflater)
        setContentView(bonusSaldoBinding.root)

        bonusSaldoViewModel.getUserToken().observe(this) {
            bonusSaldoBinding.progressBar.visibility =
                View.VISIBLE
            it?.let { idUser ->
                bonusSaldoViewModel.getUserById(idUser).observe(this) { response ->
                    when (response) {
                        is Resource.Loading -> bonusSaldoBinding.progressBar.visibility =
                            View.VISIBLE

                        is Resource.Success -> {
                            bonusSaldoBinding.progressBar.visibility = View.GONE
                            val data = response.data

                            data?.let {
                                bonusSaldoBinding.apply {
                                    tvNameUser.text = it.name
                                    tvBalanceUser.text = Formatter.rupiahFormatter(it.balance)
                                }
                            }
                        }

                        is Resource.Error -> {
                            bonusSaldoBinding.progressBar.visibility = View.GONE
                            Toast(
                                this@BonusSaldoActivity
                            ).showCustomToast(
                                true,
                                response.message.toString(),
                                this@BonusSaldoActivity
                            )
                        }
                    }
                }
            }
        }

        bonusSaldoBinding.btnStartFly.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}