package com.test.jetpackcompose.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {

    var email by remember { mutableStateOf("eve.holt@reqres.in") }
    var password by remember { mutableStateOf("cityslicka") }

    val state by viewModel.loginState.collectAsStateWithLifecycle()

    Column {

        OutlinedTextField(value = email, onValueChange = {
            email = it
        }, label = {
            Text(text = "Usuario")
        })

        OutlinedTextField(value = password, onValueChange = {
            password = it
        }, label = {
            Text(text = "Password")
        })
    var value=""
        Button(onClick = {
            viewModel.login(email, password)
            when (state) {

                is LoginState.Idle -> {
                    Log.i("Login State->", "IDLE")
                    value=LoginState.Idle.toString()
                }

                is LoginState.Loading -> {
                    value=LoginState.Loading.toString()
                    Log.i("Login State->", "Navigation Loading...")
                }

                is LoginState.Success -> {
                  value="Success!!!"
                    Log.i("Login State->", "Login success")
                }

                is LoginState.Error -> {
                    value="Error!!!"
                    Log.i("Login State->", "ERROR")
                }

            }


        }) {
            Text(text = "Login")
        }

    ShowingResponse(value)
    }
}

@Composable
fun ShowingResponse(value: String) {
    Box(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = value,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}