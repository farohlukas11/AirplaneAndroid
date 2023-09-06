package com.faroh.airplaneandroid.core.utils

import com.faroh.airplaneandroid.core.data.source.remote.response.DataResponseDestination
import com.faroh.airplaneandroid.core.data.source.remote.response.ResponseDestination
import com.faroh.airplaneandroid.core.data.source.remote.response.ResponseUser
import com.faroh.airplaneandroid.core.domain.model.CheckoutModel
import com.faroh.airplaneandroid.core.domain.model.DestinationModel
import com.faroh.airplaneandroid.core.domain.model.UserModel

object Mapper {

    fun mapUserResponseToModel(user: ResponseUser): UserModel = UserModel(
        email = user.email,
        name = user.name,
        hobby = user.hobby ?: "",
        balance = user.balance ?: 0
    )

    fun mapDestinationResponseToModel(list: List<DataResponseDestination>): List<DestinationModel> =
        list.map {
            val data = it.responseDestination
            DestinationModel(
                id = it.id,
                name = data?.name,
                imageUrl = data?.imageUrl,
                price = data?.price,
                city = data?.city,
                rating = data?.rating
            )
        }

    private fun mapDestinationModelToJson(destinationModel: DestinationModel): HashMap<String, Any> {
        val destination = HashMap<String, Any>()
        destination["city"] = destinationModel.city!!
        destination["id"] = destinationModel.id!!
        destination["imageUrl"] = destinationModel.imageUrl!!
        destination["name"] = destinationModel.name!!
        destination["price"] = destinationModel.price!!
        destination["rating"] = destinationModel.rating!!

        return destination
    }

    fun mapUserModelToJson(userModel: UserModel): HashMap<String, Any> {
        val user = HashMap<String, Any>()
        user["balance"] = userModel.balance
        user["email"] = userModel.email!!
        user["hobby"] = userModel.hobby
        user["name"] = userModel.name!!

        return user
    }

    fun mapCheckoutModelToJson(checkoutModel: CheckoutModel): HashMap<String, Any> {
        val checkout = HashMap<String, Any>()
        checkout["amountOrTraveler"] = checkoutModel.amountOrTraveler!!
        checkout["destination"] = mapDestinationModelToJson(checkoutModel.destination!!)
        checkout["grandTotal"] = checkoutModel.grandTotal!!
        checkout["insurance"] = checkoutModel.insurance!!
        checkout["price"] = checkoutModel.price!!
        checkout["refundable"] = checkoutModel.refundable!!
        checkout["selectedSeats"] = checkoutModel.selectedSeats!!
        checkout["vat"] = checkoutModel.vat!!

        return checkout
    }
}