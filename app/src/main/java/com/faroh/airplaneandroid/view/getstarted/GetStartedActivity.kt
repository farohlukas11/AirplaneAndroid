package com.faroh.airplaneandroid.view.getstarted

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.faroh.airplaneandroid.R
import com.faroh.airplaneandroid.databinding.ActivityGetStartedBinding
import com.faroh.airplaneandroid.view.signup.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetStartedActivity : AppCompatActivity() {
    private lateinit var getStartedBinding: ActivityGetStartedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getStartedBinding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(getStartedBinding.root)

        getStartedBinding.btnGetStarted.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }
}