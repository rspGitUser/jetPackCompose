package com.test.jetpackcompose.data.repository

import com.test.jetpackcompose.data.model.login.LoginRequest
import com.test.jetpackcompose.data.model.login.LoginResponse
import com.test.jetpackcompose.data.model.singleuser.SingleData
import com.test.jetpackcompose.data.model.singleuser.SingleUserData
import com.test.jetpackcompose.data.model.userlist.UserList
import com.test.jetpackcompose.data.remote.ApiClient
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

class UserRepository {

    private val apiService = ApiClient.apiService

    suspend fun login(email: String, password: String): Response<LoginResponse> {
        return apiService.login(LoginRequest(email, password))
    }

    suspend fun getUserList(
        @Query("page") page:Int,
        @Query("per_page") per_page:Int
    ): Response<UserList>{
        return  apiService.getUserList(page,per_page)
    }

    suspend fun getSingleUser(
        @Path("userId") userId:Int
    ):Response<SingleUserData>
    {
        return apiService.getSingleUser(userId)
    }
}