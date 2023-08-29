package com.faroh.airplaneandroid.core.di

import com.faroh.airplaneandroid.core.data.AirplaneRepository
import com.faroh.airplaneandroid.core.domain.repository.IAirplaneRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideAirplaneRepository(airplaneRepository: AirplaneRepository): IAirplaneRepository
}