package com.test.jetpackcompose.data.repository

import com.test.jetpackcompose.data.model.login.LoginRequest
import com.test.jetpackcompose.data.model.login.LoginResponse
import com.test.jetpackcompose.data.model.userlist.Data
import com.test.jetpackcompose.data.model.userlist.UserList
import com.test.jetpackcompose.data.remote.ApiClient
import retrofit2.Response

class UserRepository {

    private val apiService = ApiClient.apiService

    suspend fun login(email: String, password: String): Response<LoginResponse> {
        return apiService.login(LoginRequest(email, password))
    }

    suspend fun getUserList(): Response<UserList>{
        return  apiService.getUserList()
    }
}