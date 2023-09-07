package com.faroh.airplaneandroid.core.data.source.remote.response

import com.faroh.airplaneandroid.core.domain.model.DestinationModel
import com.google.firebase.firestore.PropertyName

data class ResponseTransaction(
    @get: PropertyName("amountOrTraveler") @set: PropertyName("amountOrTraveler") var amountOrTraveler: Int? = null,
    @get: PropertyName("destination") @set: PropertyName("destination") var destination: ResponseDestination? = null,
    @get: PropertyName("grandTotal") @set: PropertyName("grandTotal") var grandTotal: Double? = null,
    @get: PropertyName("insurance") @set: PropertyName("insurance") var insurance: Boolean? = null,
    @get: PropertyName("price") @set: PropertyName("price") var price: Int? = null,
    @get: PropertyName("refundable") @set: PropertyName("refundable") var refundable: Boolean? = null,
    @get: PropertyName("selectedSeats") @set: PropertyName("selectedSeats") var selectedSeats: String? = null,
    @get: PropertyName("vat") @set: PropertyName("vat") var vat: Double? = null
)