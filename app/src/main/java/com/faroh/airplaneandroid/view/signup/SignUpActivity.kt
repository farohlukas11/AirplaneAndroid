package com.faroh.airplaneandroid.view.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.faroh.airplaneandroid.core.data.Resource
import com.faroh.airplaneandroid.core.domain.model.SignUpBody
import com.faroh.airplaneandroid.core.utils.ToastUtils.showCustomToast
import com.faroh.airplaneandroid.databinding.ActivitySignUpBinding
import com.faroh.airplaneandroid.view.signin.SignInActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private lateinit var signUpBinding: ActivitySignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(signUpBinding.root)


        signUpBinding.btnSignIn.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
        }

        signUpBinding.btnSignUp.setOnClickListener {
            val email = signUpBinding.etEmail.text.toString()
            val fullName = signUpBinding.etFullName.text.toString()
            val password = signUpBinding.etPassword.text.toString()
            val hobby = signUpBinding.etHobby.text.toString()

            when {
                email.isEmpty() && fullName.isEmpty() && password.isEmpty() && hobby.isEmpty() -> Toast(
                    this@SignUpActivity
                ).showCustomToast(true, "input must be required filled", this@SignUpActivity)

                else -> {
                    signUpBinding.progressBar.visibility =
                        View.VISIBLE
                    signUpViewModel.signUp(
                        SignUpBody(
                            email = email,
                            password = password,
                            name = fullName,
                            hobby = hobby
                        )
                    ).observe(this@SignUpActivity) { response ->
                        when (response) {
                            is Resource.Loading -> signUpBinding.progressBar.visibility =
                                View.VISIBLE

                            is Resource.Success -> {
                                signUpBinding.progressBar.visibility = View.GONE
                                val data = response.data

                                data?.let {
                                    Toast(
                                        this@SignUpActivity
                                    ).showCustomToast(
                                        false,
                                        "Success SignUp data ${it.name}",
                                        this@SignUpActivity
                                    )

                                    signUpViewModel.setUser(it)

                                    startActivity(
                                        Intent(
                                            this@SignUpActivity,
                                            SignInActivity::class.java
                                        )
                                    )
                                }
                            }

                            is Resource.Error -> {
                                signUpBinding.progressBar.visibility = View.GONE
                                Toast(
                                    this@SignUpActivity
                                ).showCustomToast(
                                    true,
                                    response.message.toString(),
                                    this@SignUpActivity
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}