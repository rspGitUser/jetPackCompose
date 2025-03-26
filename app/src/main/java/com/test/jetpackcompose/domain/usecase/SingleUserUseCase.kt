package com.test.jetpackcompose.domain.usecase
import com.test.jetpackcompose.data.model.userlist.SingleUser
import com.test.jetpackcompose.data.repository.UserRepository
import retrofit2.Response

class SingleUserUseCase(private val repository: UserRepository) {

    suspend fun getSingleUser(userId:Int): Response<SingleUser> {
        return repository.getSingleUser(userId)
    }

}