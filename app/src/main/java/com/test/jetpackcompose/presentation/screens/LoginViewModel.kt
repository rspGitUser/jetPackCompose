package com.test.jetpackcompose.presentation.screens

import android.annotation.SuppressLint
import android.net.http.HttpException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.jetpackcompose.data.model.userlist.Data
import com.test.jetpackcompose.data.repository.UserRepository
import com.test.jetpackcompose.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class LoginState {
    object Init : LoginState()
    object Loading : LoginState()
    data class Success(val token: String) : LoginState()
    data class Error(val message: String) : LoginState()

}



class LoginViewModel : ViewModel() {

    private val loginUseCase = LoginUseCase(UserRepository())

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Init)

    val loginState: StateFlow<LoginState> = _loginState



    fun login(email: String, password: String) {

        viewModelScope.launch {
            Log.i("CalledViewModel->","CalledViewModel")
          _loginState.value = LoginState.Init
            try {
                val response = loginUseCase.execute(email, password)

                if (response.isSuccessful) {
                    _loginState.value = LoginState.Success(response.body()?.token ?: "No token found")

                } else {
                    _loginState.value = LoginState.Error("Error: ${response.message()}")

                }
            } catch (@SuppressLint("NewApi", "LocalSuppress") e: HttpException) {
                _loginState.value = LoginState.Error("Network Error: ${e.message}")
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("Unexpected Error: ${e.message}")
            }

        }
    }
}