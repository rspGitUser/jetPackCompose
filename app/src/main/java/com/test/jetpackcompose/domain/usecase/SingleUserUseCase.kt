package com.test.jetpackcompose.domain.usecase
import com.test.jetpackcompose.data.model.singleuser.SingleData
import com.test.jetpackcompose.data.model.singleuser.SingleUserData
import com.test.jetpackcompose.data.repository.UserRepository
import retrofit2.Response

/*
class UserListUseCase(private val repository: UserRepository) {
    suspend fun execute(page:Int,per_page:Int): Response<UserList> {
        return repository.getUserList(page,per_page)
}
}
 */
class SingleUserUseCase(private val repository: UserRepository) {

    suspend fun execute(userId:Int): Response<SingleUserData> {
        return repository.getSingleUser(userId)
    }

}