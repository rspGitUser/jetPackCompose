package com.test.jetpackcompose.domain.usecase

import com.test.jetpackcompose.data.model.login.LoginResponse
import com.test.jetpackcompose.data.model.userlist.Data
import com.test.jetpackcompose.data.model.userlist.UserList
import com.test.jetpackcompose.data.repository.UserRepository
import retrofit2.Response

class UserListUseCase(private val repository: UserRepository) {

    suspend fun execute(page:Int,per_page:Int): Response<UserList> {
        return repository.getUserList(page,per_page)

    }



}