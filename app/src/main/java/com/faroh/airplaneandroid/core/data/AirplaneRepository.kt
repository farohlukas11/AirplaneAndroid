package com.faroh.airplaneandroid.core.data

import android.annotation.SuppressLint
import com.faroh.airplaneandroid.core.data.source.preferences.AirplanePreference
import com.faroh.airplaneandroid.core.data.source.remote.RemoteDataSource
import com.faroh.airplaneandroid.core.data.source.remote.response.ApiResponse
import com.faroh.airplaneandroid.core.domain.model.CheckoutModel
import com.faroh.airplaneandroid.core.domain.model.DestinationModel
import com.faroh.airplaneandroid.core.domain.model.SignInBody
import com.faroh.airplaneandroid.core.domain.model.SignUpBody
import com.faroh.airplaneandroid.core.domain.model.UserModel
import com.faroh.airplaneandroid.core.domain.repository.IAirplaneRepository
import com.faroh.airplaneandroid.core.utils.Mapper
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AirplaneRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val preference: AirplanePreference
) : IAirplaneRepository {
    @SuppressLint("CheckResult")
    override fun signUp(signUpBody: SignUpBody): Flowable<Resource<UserModel>> {
        val result = PublishSubject.create<Resource<UserModel>>()

        result.onNext(Resource.Loading())
        remoteDataSource.signUp(signUpBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe {
                when (it) {
                    is ApiResponse.Success -> result.onNext(Resource.Success(it.data))
                    is ApiResponse.Empty -> result.onNext(Resource.Success(null))
                    is ApiResponse.Error -> result.onNext(Resource.Error(it.errorMessage))
                }
            }
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    override fun signIn(signInBody: SignInBody): Flowable<Resource<String>> {
        val result = PublishSubject.create<Resource<String>>()

        result.onNext(Resource.Loading())
        remoteDataSource.signIn(signInBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe {
                when (it) {
                    is ApiResponse.Success -> result.onNext(Resource.Success(it.data))
                    is ApiResponse.Empty -> result.onNext(Resource.Success(null))
                    is ApiResponse.Error -> result.onNext(Resource.Error(it.errorMessage))
                }
            }
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    override fun signOut() {
        remoteDataSource.signOut()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    override fun setUser(userModel: UserModel) {
        remoteDataSource.setUser(userModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    @SuppressLint("CheckResult")
    override fun getUserById(id: String): Flowable<Resource<UserModel>> {
        val result = PublishSubject.create<Resource<UserModel>>()

        result.onNext(Resource.Loading())
        remoteDataSource.getUserById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe {
                when (it) {
                    is ApiResponse.Success -> result.onNext(
                        Resource.Success(
                            Mapper.mapUserResponseToModel(
                                it.data
                            )
                        )
                    )

                    is ApiResponse.Empty -> result.onNext(Resource.Success(null))
                    is ApiResponse.Error -> result.onNext(Resource.Error(it.errorMessage))
                }
            }
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    override suspend fun setUserToken(token: String) {
        preference.setUserToken(token)
    }

    override fun getUserToken(): Flow<String> = preference.getUserToken()

    override suspend fun setUserState(state: Boolean) {
        preference.setUserState(state)
    }

    override fun getUserState(): Flow<Boolean> = preference.getUserState()

    @SuppressLint("CheckResult")
    override fun getAllDestination(): Flowable<Resource<List<DestinationModel>>> {
        val result = PublishSubject.create<Resource<List<DestinationModel>>>()

        result.onNext(Resource.Loading())
        remoteDataSource.getAllDestination()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is ApiResponse.Success -> result.onNext(
                        Resource.Success(
                            Mapper.mapDestinationResponseToModel(
                                it.data
                            )
                        )
                    )

                    is ApiResponse.Empty -> result.onNext(Resource.Success(null))
                    is ApiResponse.Error -> result.onNext(Resource.Error(it.errorMessage))
                }
            }
        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    override fun checkoutDestination(checkoutModel: CheckoutModel) {
        remoteDataSource.checkoutDestination(checkoutModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}