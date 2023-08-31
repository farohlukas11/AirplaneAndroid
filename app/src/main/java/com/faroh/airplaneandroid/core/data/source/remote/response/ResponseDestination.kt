package com.faroh.airplaneandroid.core.data.source.remote.response

import com.google.firebase.firestore.PropertyName

data class ResponseDestination(
    @get: PropertyName("city") @set: PropertyName("city") var city: String? = null,
    @get: PropertyName("imageUrl") @set: PropertyName("imageUrl") var imageUrl: String? = null,
    @get: PropertyName("name") @set: PropertyName("name") var name: String? = null,
    @get: PropertyName("price") @set: PropertyName("price") var price: Int? = null,
    @get: PropertyName("rating") @set: PropertyName("rating") var rating: Double? = null,
)