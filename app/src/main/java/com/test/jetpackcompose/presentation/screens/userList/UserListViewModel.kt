package com.test.jetpackcompose.presentation.screens.userList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.jetpackcompose.data.model.singleuser.SingleData
import com.test.jetpackcompose.data.model.userlist.Data
import com.test.jetpackcompose.data.repository.UserRepository
import com.test.jetpackcompose.domain.usecase.SingleUserUseCase
import com.test.jetpackcompose.domain.usecase.UserListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody.Companion.toResponseBody


sealed class UserListState {
    object Loading : UserListState()
    object Init : UserListState()
    data class Success(val users: List<Data>?) : UserListState()
    object Error : UserListState()
}

sealed class SingleUserState {
    object Loading : SingleUserState()
    object Init : SingleUserState()
    data class Success(val userData: SingleData) : SingleUserState()
    object Error : SingleUserState()
}

class UserListViewModel : ViewModel() {
    private val userListUseCase = UserListUseCase(UserRepository())
    private val _userListState =
        MutableStateFlow<UserListState>(UserListState.Init) // Inicializa correctamente aquí
    val userListState: StateFlow<UserListState> = _userListState

    val userListData = MutableStateFlow(emptyList<Data>())

    //SingleUser
    //Ingle user
    private val singleUserUseCase = SingleUserUseCase(UserRepository())
    private val _singleUserState = MutableStateFlow<SingleUserState>(SingleUserState.Init)
    val singleUserState: StateFlow<SingleUserState> = _singleUserState


    val singleUserData = MutableStateFlow(SingleData("","","",0,""))

    fun getSingleUser(userId: Int) {

        try {
            _singleUserState.value=SingleUserState.Loading

            viewModelScope.launch {
                Log.i("LaunchVieModel", "True")
                _singleUserState.value = SingleUserState.Loading
                Log.i(
                    "Step1->", """
                ViewModelCall-> {True}
                SelectedId->${userId}
                
                
            """.trimIndent()
                )


                Log.i("Step2->", "Calling service useCase")



                val response = singleUserUseCase.execute(userId)

                Log.i("Step3->","Creating data object")

                val data=response?.body()?.data?:emptyList<SingleData>()

                Log.i("Step4->", "Fullfiling view model states")


                singleUserData.value= data as SingleData
                _singleUserState.value=SingleUserState.Success(data)
                Log.i("singleUserData value", singleUserData.value.avatar)






            }

        } catch (error: Error) {
            Log.i("Response Error->", error.message.toString())

        }

    }


    fun getUserList() {
        viewModelScope.launch {
            _userListState.value = UserListState.Loading
            val response = userListUseCase.execute(1, 12)
            if (response.isSuccessful) {
                val data = response.body()?.data ?: emptyList()
                userListData.value = data
                _userListState.value = UserListState.Success(data)
                Log.i("Log->", "LoginSuccess")
            } else {
                Log.e("Error->", "Error fetching user list: ${response.message()}")
                _userListState.value = UserListState.Error
            }
        }
    }
}