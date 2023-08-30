package com.faroh.airplaneandroid.core.di

import android.content.Context
import com.faroh.airplaneandroid.core.data.source.preferences.AirplanePreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferencesModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): AirplanePreference =
        AirplanePreference(context)
}