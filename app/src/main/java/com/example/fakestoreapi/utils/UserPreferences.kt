package com.example.fakestoreapi.utils

import android.content.Context

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


class UserPreferences (
    val  context: Context
) {
    private val Context.dataStore by preferencesDataStore("app_preferences")

    private val applicationContext = context.applicationContext



    val getUserId: Flow<String?>
        get() = applicationContext.dataStore.data.map { preferences ->
            preferences[KEY_ID]
        }

    suspend fun saveUserId(bookmark: String) {
        applicationContext.dataStore.edit { preferences ->
            preferences[KEY_ID] = bookmark
        }
    }


    companion object {
        val KEY_ID = stringPreferencesKey("key_id")
    }

}