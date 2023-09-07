package com.faroh.airplaneandroid.view.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.toLiveData
import com.faroh.airplaneandroid.core.domain.model.TransactionModel
import com.faroh.airplaneandroid.core.domain.usecase.AirplaneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val airplaneUseCase: AirplaneUseCase) :
    ViewModel() {

    fun checkoutDestination(transactionModel: TransactionModel) =
        airplaneUseCase.checkoutDestination(transactionModel)

    fun getUserToken() = airplaneUseCase.getUserToken().asLiveData(Dispatchers.IO)

    fun getUserById(id: String) = airplaneUseCase.getUserById(id).toLiveData()

    fun updateUserBalance(id: String, balance: Double) = airplaneUseCase.updateUserBalance(id, balance)
}