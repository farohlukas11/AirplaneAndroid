package com.faroh.airplaneandroid.view.bonus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.toLiveData
import com.faroh.airplaneandroid.core.domain.usecase.AirplaneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class BonusSaldoViewModel @Inject constructor(private val airplaneUseCase: AirplaneUseCase) :
    ViewModel() {

    fun getUserToken() = airplaneUseCase.getUserToken().asLiveData(Dispatchers.IO)

    fun getUserById(id: String) = airplaneUseCase.getUserById(id).toLiveData()
}