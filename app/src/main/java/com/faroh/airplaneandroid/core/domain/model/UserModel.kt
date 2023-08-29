package com.faroh.airplaneandroid.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val id: String? = null,
    val email: String? = null,
    val name: String? = null,
    val hobby: String = "",
    val balance: Int = 0
) : Parcelable
