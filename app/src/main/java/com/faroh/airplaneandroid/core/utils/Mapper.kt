package com.faroh.airplaneandroid.core.utils

import com.faroh.airplaneandroid.core.data.source.remote.response.DataResponseDestination
import com.faroh.airplaneandroid.core.data.source.remote.response.ResponseTransaction
import com.faroh.airplaneandroid.core.data.source.remote.response.ResponseUser
import com.faroh.airplaneandroid.core.domain.model.TransactionModel
import com.faroh.airplaneandroid.core.domain.model.DestinationModel
import com.faroh.airplaneandroid.core.domain.model.UserModel

object Mapper {

    fun mapUserResponseToModel(user: ResponseUser): UserModel = UserModel(
        id = "",
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

    fun mapTransactionModelToJson(transactionModel: TransactionModel): HashMap<String, Any> {
        val checkout = HashMap<String, Any>()
        checkout["amountOrTraveler"] = transactionModel.amountOrTraveler!!
        checkout["destination"] = mapDestinationModelToJson(transactionModel.destination!!)
        checkout["grandTotal"] = transactionModel.grandTotal
        checkout["insurance"] = transactionModel.insurance!!
        checkout["price"] = transactionModel.price!!
        checkout["refundable"] = transactionModel.refundable!!
        checkout["selectedSeats"] = transactionModel.selectedSeats!!
        checkout["vat"] = transactionModel.vat!!

        return checkout
    }

    fun mapTransactionResponseToModel(list: List<ResponseTransaction>): List<TransactionModel> =
        list.map {
            TransactionModel(
                amountOrTraveler = it.amountOrTraveler,
                destination = DestinationModel(
                    id = "",
                    name = it.destination?.name,
                    imageUrl = it.destination?.imageUrl,
                    price = it.destination?.price,
                    city = it.destination?.city,
                    rating = it.destination?.rating
                ),
                grandTotal = it.grandTotal!!,
                insurance = it.insurance,
                price = it.price,
                refundable = it.refundable,
                selectedSeats = it.selectedSeats,
                vat = it.vat
            )
        }
}