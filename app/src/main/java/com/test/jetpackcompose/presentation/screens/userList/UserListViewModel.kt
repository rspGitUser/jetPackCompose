package com.test.jetpackcompose.presentation.screens.userList

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.jetpackcompose.data.model.userlist.Data
import com.test.jetpackcompose.data.repository.UserRepository
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

class UserListViewModel : ViewModel() {
    private val userListUseCase = UserListUseCase(UserRepository())
    private val _userListState = MutableStateFlow<UserListState>(UserListState.Init) // Inicializa correctamente aquí
    val userListState: StateFlow<UserListState> = _userListState

    val userListData = MutableStateFlow(emptyList<Data>())

    fun getUserList() {
        viewModelScope.launch {
            _userListState.value = UserListState.Loading
            val response = userListUseCase.execute()
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