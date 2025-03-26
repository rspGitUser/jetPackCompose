package com.test.jetpackcompose.presentation.screens.userList

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.jetpackcompose.data.model.singleuser.SingleUser
import com.test.jetpackcompose.data.model.userlist.Data
import com.test.jetpackcompose.data.repository.UserRepository
import com.test.jetpackcompose.domain.usecase.SingleUserUseCase
import com.test.jetpackcompose.domain.usecase.UserListUseCase
import com.test.jetpackcompose.presentation.screens.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class UserListState {
    object Loading : UserListState()
    object Init: UserListState()
    data class Success(val users: List<Data>?) : UserListState()
    object  Error : UserListState()
}

sealed class SingleUserState{
    object Loading : SingleUserState()
    object Init: SingleUserState()
    data class Success(val userData:  List<Data>): SingleUserState()
    object  Error : SingleUserState()
}
class UserListViewModel : ViewModel() {
    private val userListUseCase = UserListUseCase(UserRepository())
    private val _userListState = MutableStateFlow<UserListState>(UserListState.Init) // Inicializa correctamente aquí
    val userListState: StateFlow<UserListState> = _userListState

    val userListData = MutableStateFlow(emptyList<Data>())

    //SingleUser
    //Ingle user
    private val singleUserUseCase=SingleUserUseCase(UserRepository())
    private val _singleUserState= MutableStateFlow<SingleUserState>(SingleUserState.Init)
    val singleUserState: StateFlow<SingleUserState> = _singleUserState

    val showPopup= MutableStateFlow<Boolean>(false)

    val singleUserData = MutableStateFlow(emptyList<Data>())
    val userId=MutableStateFlow<Int>(0)
    fun getSingleUser(userId:Int) {
        viewModelScope.launch {
            _singleUserState.value = SingleUserState.Loading
            val response = singleUserUseCase.getSingleUser(userId)
            if (response.isSuccessful) {
                val data = response.body()?.data ?: emptyList()
                singleUserData.value = data
                _singleUserState.value = SingleUserState.Success(data)
                Log.i("Log->","Single user success")
            } else {
                Log.e("Error->", "Error fetching single user: ${response.message()}")
                _singleUserState.value = SingleUserState.Error
            }
        }
    }
    fun getUserList() {
        viewModelScope.launch {
            _userListState.value = UserListState.Loading
            val response = userListUseCase.execute(1,2)
            if (response.isSuccessful) {
                val data = response.body()?.data ?: emptyList()
                userListData.value = data
                _userListState.value = UserListState.Success(data)
                Log.i("Log->","LoginSuccess")
            } else {
                Log.e("Error->", "Error fetching user list: ${response.message()}")
                _userListState.value = UserListState.Error
            }
        }
    }
}