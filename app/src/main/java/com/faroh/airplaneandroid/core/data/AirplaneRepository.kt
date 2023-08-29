package com.faroh.airplaneandroid.core.data

import com.faroh.airplaneandroid.core.data.source.remote.RemoteDataSource
import com.faroh.airplaneandroid.core.domain.repository.IAirplaneRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AirplaneRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : IAirplaneRepository {


}