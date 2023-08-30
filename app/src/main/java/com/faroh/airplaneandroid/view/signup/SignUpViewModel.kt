package com.faroh.airplaneandroid.view.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.faroh.airplaneandroid.core.domain.model.SignUpBody
import com.faroh.airplaneandroid.core.domain.usecase.AirplaneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val airplaneUseCase: AirplaneUseCase) :
    ViewModel() {

    fun signUp(signUpBody: SignUpBody) = airplaneUseCase.signUp(signUpBody).toLiveData()
}