package com.faroh.airplaneandroid.view.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.faroh.airplaneandroid.R
import com.faroh.airplaneandroid.core.domain.model.SignUpBody
import com.faroh.airplaneandroid.databinding.ActivitySignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private lateinit var signUpBinding: ActivitySignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(signUpBinding.root)

        signUpBinding.apply {
            val email = etEmail.text.toString()
            val fullName = etFullName.text.toString()
            val password = etPassword.text.toString()
            val hobby = etHobby.text.toString()

            btnSignUp.setOnClickListener {
                if ()
                signUpViewModel.signUp(
                    SignUpBody(
                        email = email,
                        password = password,
                        name = fullName,
                        hobby = hobby
                    )
                ).observe(this@SignUpActivity) {

                }
            }
        }
    }
}