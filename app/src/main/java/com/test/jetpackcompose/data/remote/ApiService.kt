package com.test.jetpackcompose.data.remote

import com.test.jetpackcompose.data.model.login.LoginRequest
import com.test.jetpackcompose.data.model.login.LoginResponse
import com.test.jetpackcompose.data.model.singleuser.SingleData
import com.test.jetpackcompose.data.model.singleuser.SingleUserData
import com.test.jetpackcompose.data.model.userlist.Data
import com.test.jetpackcompose.data.model.userlist.UserList

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

//@GET("users?page=1&per_page=12")
//suspend fun getUserList():Response<UserList>
@GET("users")
suspend fun getUserList(
    @Query("page")page:Int,
    @Query("per_page") per_page:Int
):Response<UserList>

@GET("users/{userId}")
suspend fun getSingleUser(
    @Path("userId") userId:Int

):Response<SingleUserData>

}