package com.faroh.airplaneandroid.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CheckoutModel(
    val amountOrTraveler: Int?,
    val destination: DestinationModel?,
    val grandTotal: Double,
    val insurance: Boolean? = true,
    val price: Int?,
    val refundable: Boolean? = false,
    val selectedSeats: String?,
    val vat: Double?
) : Parcelable
