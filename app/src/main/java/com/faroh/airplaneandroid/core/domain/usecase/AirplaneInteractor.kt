package com.faroh.airplaneandroid.core.domain.usecase

import com.faroh.airplaneandroid.core.data.AirplaneRepository
import com.faroh.airplaneandroid.core.data.Resource
import com.faroh.airplaneandroid.core.domain.model.SignInBody
import com.faroh.airplaneandroid.core.domain.model.SignUpBody
import com.faroh.airplaneandroid.core.domain.model.UserModel
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AirplaneInteractor @Inject constructor(private val airplaneRepository: AirplaneRepository) :
    AirplaneUseCase {
    override fun signUp(signUpBody: SignUpBody): Flowable<Resource<UserModel>> {
        return airplaneRepository.signUp(signUpBody)
    }

    override fun signIn(signInBody: SignInBody): Flowable<Resource<String>> {
        return airplaneRepository.signIn(signInBody)
    }

    override fun signOut() {
        airplaneRepository.signOut()
    }

    override fun setUser(userModel: UserModel) {
        airplaneRepository.setUser(userModel)
    }

    override fun getUserById(id: String): Flowable<Resource<UserModel>> {
        return airplaneRepository.getUserById(id)
    }

    override suspend fun setUserToken(token: String) {
        airplaneRepository.setUserToken(token)
    }

    override fun getUserToken(): Flow<String> {
        return airplaneRepository.getUserToken()
    }

    override suspend fun setUserState(state: Boolean) {
        airplaneRepository.setUserState(state)
    }

    override fun getUserState(): Flow<Boolean> {
        return airplaneRepository.getUserState()
    }
}