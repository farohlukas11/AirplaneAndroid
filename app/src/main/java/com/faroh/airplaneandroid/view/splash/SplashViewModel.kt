package com.faroh.airplaneandroid.view.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.faroh.airplaneandroid.core.domain.usecase.AirplaneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val airplaneUseCase: AirplaneUseCase) :
    ViewModel() {

    val stateUser = airplaneUseCase.getUserState().asLiveData(Dispatchers.IO)

}