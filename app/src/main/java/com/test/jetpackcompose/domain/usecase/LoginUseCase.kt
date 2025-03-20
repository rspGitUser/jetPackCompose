package com.test.jetpackcompose.domain.usecase

import com.test.jetpackcompose.data.model.login.LoginResponse
import com.test.jetpackcompose.data.repository.UserRepository
import retrofit2.Response


class LoginUseCase(private val repository: UserRepository) {

    suspend fun execute(email: String, password: String): Response<LoginResponse> {
        return repository.login(email, password)
    }
}