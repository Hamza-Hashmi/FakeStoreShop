package com.example.fakestoreapi.utils

import android.app.Application
import android.content.Context
import com.example.fakestoreapi.network.FakeStoreAPI
import com.example.fakestoreapi.network.RetrofitInstance
import com.example.fakestoreapi.utils.UserPreferences
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@HiltAndroidApp
class MyApplication:Application() {

}