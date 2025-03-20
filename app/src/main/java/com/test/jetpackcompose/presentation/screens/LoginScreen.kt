package com.test.jetpackcompose.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {

    var email by remember { mutableStateOf("eve.holt@reqres.in") }
    var password by remember { mutableStateOf("cityslicka") }

    val state by remember {
        mutableStateOf(viewModel.loginState)
    }

    Column {

        OutlinedTextField(value = email, onValueChange ={
            email=it
        }, label = {
            Text(text = "Usuario")
        } )

        OutlinedTextField(value = password, onValueChange ={
            password=it
        }, label = {
            Text(text = "Password")
        } )

        Button(onClick = {
            viewModel.login(email,password)

        }) {
            Text(text = "Login")
        }
    }
}