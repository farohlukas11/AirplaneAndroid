package com.faroh.airplaneandroid.view.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faroh.airplaneandroid.core.domain.usecase.AirplaneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val airplaneUseCase: AirplaneUseCase) :
    ViewModel() {

    fun signOut() = airplaneUseCase.signOut()

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