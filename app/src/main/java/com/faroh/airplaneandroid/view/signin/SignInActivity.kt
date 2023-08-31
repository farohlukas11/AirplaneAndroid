package com.faroh.airplaneandroid.view.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.faroh.airplaneandroid.core.data.Resource
import com.faroh.airplaneandroid.core.domain.model.SignInBody
import com.faroh.airplaneandroid.core.utils.ToastUtils.showCustomToast
import com.faroh.airplaneandroid.databinding.ActivitySignInBinding
import com.faroh.airplaneandroid.view.bonus.BonusSaldoActivity
import com.faroh.airplaneandroid.view.signup.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {

    private lateinit var signInBinding: ActivitySignInBinding
    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signInBinding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(signInBinding.root)

        signInBinding.btnSignUp.setOnClickListener {
            startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
        }

        signInBinding.btnSignIn.setOnClickListener {
            val email = signInBinding.etEmail.text.toString()
            val password = signInBinding.etPassword.text.toString()
                
            when {
                email.isEmpty() && password.isEmpty() -> Toast(
                    this@SignInActivity
                ).showCustomToast(true, "input must be required filled", this@SignInActivity)

                else -> {
                    signInViewModel.signIn(
                        SignInBody(
                            email = email,
                            password = password
                        )
                    ).observe(this@SignInActivity) { response ->
                        when (response) {
                            is Resource.Loading -> signInBinding.progressBar.visibility =
                                View.VISIBLE

                            is Resource.Success -> {
                                signInBinding.progressBar.visibility =
                                    View.GONE
                                val data = response.data

                                data?.let {
                                    Toast(
                                        this@SignInActivity
                                    ).showCustomToast(
                                        false,
                                        "Authenticated",
                                        this@SignInActivity
                                    )

                                    signInViewModel.setUserToken(it)
                                    signInViewModel.setUserState(true)

                                    startActivity(
                                        Intent(
                                            this@SignInActivity,
                                            BonusSaldoActivity::class.java
                                        )
                                    )
                                    finish()
                                }
                            }

                            is Resource.Error -> Toast(
                                this@SignInActivity
                            ).showCustomToast(
                                true,
                                response.message.toString(),
                                this@SignInActivity
                            )
                        }
                    }
                }
            }
        }

    }
}