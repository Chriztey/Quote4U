package com.chris.quote4u.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

const val ON_BOARDING_NAME = "getStart"
const val ON_BOARDING_KEY = "getStartKey"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = ON_BOARDING_NAME)

class OnBoardingRepoImplementation @Inject constructor(
    @ApplicationContext context: Context
): OnBoardingRepo {

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = ON_BOARDING_KEY)
    }

    private val dataStore = context.dataStore

    override suspend fun saveOnBoardingState(isCompleted: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = isCompleted
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) emit(emptyPreferences())
                else throw exception
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }
}