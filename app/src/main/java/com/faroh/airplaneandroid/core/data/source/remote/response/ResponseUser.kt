package com.faroh.airplaneandroid.core.data.source.remote.response

import com.google.firebase.firestore.PropertyName

data class ResponseUser(
    @get: PropertyName("name") @set: PropertyName("name") var name: String? = null,
    @get: PropertyName("hobby") @set: PropertyName("hobby") var hobby: String? = null,
    @get: PropertyName("email") @set: PropertyName("email") var email: String? = null,
    @get: PropertyName("balance") @set: PropertyName("balance") var balance: Int? = null
)
