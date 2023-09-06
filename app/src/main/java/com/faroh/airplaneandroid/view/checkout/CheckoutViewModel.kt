package com.faroh.airplaneandroid.view.checkout

import androidx.lifecycle.ViewModel
import com.faroh.airplaneandroid.core.domain.model.CheckoutModel
import com.faroh.airplaneandroid.core.domain.usecase.AirplaneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val airplaneUseCase: AirplaneUseCase) :
    ViewModel() {

    fun checkoutDestination(checkoutModel: CheckoutModel) =
        airplaneUseCase.checkoutDestination(checkoutModel)
}