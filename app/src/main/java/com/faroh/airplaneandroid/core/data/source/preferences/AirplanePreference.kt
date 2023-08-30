package com.faroh.airplaneandroid.core.data.source.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AirplanePreference @Inject constructor(private val context: Context) {

    private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = DATA_STORE_NAME
    )

    suspend fun setUserToken(token: String) {
        context.userPreferencesDataStore.edit {
            it[USER_TOKEN] = token
        }
    }

    fun getUserToken(): Flow<String> = context.userPreferencesDataStore.data.map {
        it[USER_TOKEN] ?: ""
    }

    suspend fun setUserState(state: Boolean) {
        context.userPreferencesDataStore.edit {
            it[STATE_AUTH] = state
        }
    }

    fun getUserState(): Flow<Boolean> = context.userPreferencesDataStore.data.map {
        it[STATE_AUTH] ?: false
    }

    companion object {
        const val DATA_STORE_NAME = "airplane_android"

        private val USER_TOKEN = stringPreferencesKey("USER_TOKEN")
        private val STATE_AUTH = booleanPreferencesKey("STATE_AUTH")
    }
}