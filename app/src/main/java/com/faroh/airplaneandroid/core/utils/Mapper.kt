package com.faroh.airplaneandroid.core.utils

import com.faroh.airplaneandroid.core.data.source.remote.response.ResponseUser
import com.faroh.airplaneandroid.core.domain.model.UserModel

object Mapper {

    fun mapUserResponseToModel(user: ResponseUser): UserModel = UserModel(
        email = user.email,
        name = user.name,
        hobby = user.hobby ?: "",
        balance = user.balance ?: 0
    )
}