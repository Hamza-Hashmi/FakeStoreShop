package com.example.fakestoreapi.network


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstance {

    private const val BASE_URL = "https://fakestoreapi.com/"
    @Singleton
    @Provides
    fun getInstance():Retrofit{
     /*   val interceptor =  HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()*/
       return Retrofit.Builder()
           .baseUrl(BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
          // .client(client)
           .build()
   }

    @Singleton
    @Provides
    fun provideFakeStoreApiInterface(retrofit: Retrofit):FakeStoreAPI = retrofit.create(FakeStoreAPI::class.java)

}