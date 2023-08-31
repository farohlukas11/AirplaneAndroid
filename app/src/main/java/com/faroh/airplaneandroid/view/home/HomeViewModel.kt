package com.faroh.airplaneandroid.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.toLiveData
import com.faroh.airplaneandroid.core.data.Resource
import com.faroh.airplaneandroid.core.domain.model.DestinationModel
import com.faroh.airplaneandroid.core.domain.usecase.AirplaneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val airplaneUseCase: AirplaneUseCase) :
    ViewModel() {

    fun getUserToken() = airplaneUseCase.getUserToken().asLiveData(Dispatchers.IO)

    fun getUserById(id: String) = airplaneUseCase.getUserById(id).toLiveData()

    fun getAllDestination() = airplaneUseCase.getAllDestination().toLiveData()
}