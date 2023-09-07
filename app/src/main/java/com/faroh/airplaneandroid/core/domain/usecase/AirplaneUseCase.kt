package com.faroh.airplaneandroid.core.domain.usecase

import com.faroh.airplaneandroid.core.data.Resource
import com.faroh.airplaneandroid.core.domain.model.TransactionModel
import com.faroh.airplaneandroid.core.domain.model.DestinationModel
import com.faroh.airplaneandroid.core.domain.model.SignInBody
import com.faroh.airplaneandroid.core.domain.model.SignUpBody
import com.faroh.airplaneandroid.core.domain.model.UserModel
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface AirplaneUseCase {

    fun signUp(signUpBody: SignUpBody): Flowable<Resource<UserModel>>

    fun signIn(signInBody: SignInBody): Flowable<Resource<String>>

    fun signOut()

    fun setUser(userModel: UserModel)

    fun getUserById(id: String): Flowable<Resource<UserModel>>

    suspend fun setUserToken(token: String)

    fun getUserToken(): Flow<String>

    suspend fun setUserState(state: Boolean)

    fun getUserState(): Flow<Boolean>

    fun getAllDestination(): Flowable<Resource<List<DestinationModel>>>

    fun checkoutDestination(transactionModel: TransactionModel)

    fun updateUserBalance(id: String, balance: Double)

    fun getAllTransaction(): Flowable<Resource<List<TransactionModel>>>
}