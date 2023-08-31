package com.faroh.airplaneandroid.core.utils

import com.faroh.airplaneandroid.core.data.source.remote.response.ResponseDestination
import com.faroh.airplaneandroid.core.data.source.remote.response.ResponseUser
import com.faroh.airplaneandroid.core.domain.model.DestinationModel
import com.faroh.airplaneandroid.core.domain.model.UserModel

object Mapper {

    fun mapUserResponseToModel(user: ResponseUser): UserModel = UserModel(
        email = user.email,
        name = user.name,
        hobby = user.hobby ?: "",
        balance = user.balance ?: 0
    )

    fun mapDestinationResponseToModel(list: List<ResponseDestination>): List<DestinationModel> =
        list.map {
            DestinationModel(
                name = it.name,
                imageUrl = it.imageUrl,
                price = it.price,
                city = it.city,
                rating = it.rating
            )
        }
}