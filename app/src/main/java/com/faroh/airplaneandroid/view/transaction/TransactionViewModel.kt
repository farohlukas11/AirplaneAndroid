package com.faroh.airplaneandroid.view.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.faroh.airplaneandroid.core.data.Resource
import com.faroh.airplaneandroid.core.domain.model.TransactionModel
import com.faroh.airplaneandroid.core.domain.usecase.AirplaneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(private val airplaneUseCase: AirplaneUseCase) :
    ViewModel() {

    fun getAllTransaction() =
        airplaneUseCase.getAllTransaction().toLiveData()
}