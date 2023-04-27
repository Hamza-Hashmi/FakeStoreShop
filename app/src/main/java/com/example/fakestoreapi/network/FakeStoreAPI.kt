package com.example.fakestoreapi.network

import com.example.fakestoreapi.models.Categories
import com.example.fakestoreapi.models.ProductResponse
import com.example.fakestoreapi.models.SignUpResponse
import com.example.fakestoreapi.models.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import javax.inject.Singleton

interface FakeStoreAPI {

    @POST("users")
    suspend fun createUser(@Body user:User): Response<SignUpResponse>

    @GET("products/categories")
    suspend fun getCategories():Response<Categories>


    @GET("products/category/{category}")
    suspend fun getCategoryItems(@Path("category") category:String):Response<ProductResponse>


}