package com.faroh.airplaneandroid.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.faroh.airplaneandroid.core.domain.usecase.AirplaneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val airplaneUseCase: AirplaneUseCase) :
    ViewModel() {

    fun getAllDestination() = airplaneUseCase.getAllDestination().toLiveData()
}