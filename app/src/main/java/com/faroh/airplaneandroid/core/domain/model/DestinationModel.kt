package com.faroh.airplaneandroid.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DestinationModel(
    val name: String?,
    val imageUrl: String?,
    val price: Int?,
    val city: String?,
    val rating: Double?
) : Parcelable
