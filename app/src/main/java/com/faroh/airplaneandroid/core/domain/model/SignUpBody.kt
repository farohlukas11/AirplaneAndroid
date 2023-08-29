package com.faroh.airplaneandroid.core.domain.model

data class SignUpBody(
    val email: String,
    val password: String,
    val name: String,
    val hobby: String = ""
)
