package com.example.fakestoreapi.dataSource.repositroires

import com.example.fakestoreapi.models.SignUpResponse
import com.example.fakestoreapi.models.User
import com.example.fakestoreapi.network.FakeStoreAPI
import retrofit2.Response
import javax.inject.Inject


class RegistrationRepo @Inject constructor(val fakeStoreAPI: FakeStoreAPI) {

    suspend fun createUser(user: User) : Response<SignUpResponse> {
        return fakeStoreAPI.createUser(user)
    }
}