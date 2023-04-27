package com.example.fakestoreapi

import android.app.Application
import com.example.fakestoreapi.network.FakeStoreAPI
import com.example.fakestoreapi.network.RetrofitInstance

class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        val fakeStoreAPI= RetrofitInstance.getInstance().create(FakeStoreAPI::class.java)

    }
}