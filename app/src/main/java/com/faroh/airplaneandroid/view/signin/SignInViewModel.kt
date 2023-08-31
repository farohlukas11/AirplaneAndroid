package com.faroh.airplaneandroid.view.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import androidx.lifecycle.viewModelScope
import com.faroh.airplaneandroid.core.domain.model.SignInBody
import com.faroh.airplaneandroid.core.domain.usecase.AirplaneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val airplaneUseCase: AirplaneUseCase) :
    ViewModel() {

    fun signIn(signInBody: SignInBody) = airplaneUseCase.signIn(signInBody).toLiveData()

    fun setUserToken(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            airplaneUseCase.setUserToken(token)
        }
    }

    fun setUserState(state: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            airplaneUseCase.setUserState(state)
        }
    }
}