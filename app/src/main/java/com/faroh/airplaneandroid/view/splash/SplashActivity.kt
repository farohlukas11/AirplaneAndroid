package com.faroh.airplaneandroid.view.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.faroh.airplaneandroid.databinding.ActivitySplashBinding
import com.faroh.airplaneandroid.view.bonus.BonusSaldoActivity
import com.faroh.airplaneandroid.view.getstarted.GetStartedActivity
import com.faroh.airplaneandroid.view.signup.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var splashBinding: ActivitySplashBinding
    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)
        val delay = 2000L

        Handler(Looper.getMainLooper()).postDelayed({
            splashViewModel.stateUser.observe(this) {
                if (it) {
                    startActivity(Intent(this, BonusSaldoActivity::class.java))
                } else {
                    startActivity(Intent(this, GetStartedActivity::class.java))
                }
                finish()
            }
        }, delay)
    }
}