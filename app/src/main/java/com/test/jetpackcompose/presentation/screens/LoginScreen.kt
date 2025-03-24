package com.test.jetpackcompose.presentation.screens

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.test.jetpackcompose.presentation.components.LoginInputText
import com.test.jetpackcompose.presentation.navigation.Routes
import okhttp3.Route

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {

    var email by remember { mutableStateOf("eve.holt@reqres.in") }
    var password by remember { mutableStateOf("cityslicka") }

    val state by viewModel.loginState.collectAsStateWithLifecycle()

    var value by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 8.dp, start = 8.dp), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.Red,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        ) {

//            OutlinedTextField(value = email, onValueChange = {
//                email = it
//            }, label = {
//                Text(text = "Usuario")
//            })
            LoginInputText(value = email, label = "Usuario", OnValueChange = {
                email = it.toString()
            })

            LoginInputText(
                value = password,
                label = "Password",
                OnValueChange = { password = it.toString() })

            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                viewModel.login(email, password)
                when (state) {

                    is LoginState.Init -> {
                        Log.i("Login State->", "IDLE")
                        value = LoginState.Init.toString()
                    }

                    is LoginState.Loading -> {
                        value = LoginState.Loading.toString()
                        Log.i("Login State->", "Navigation Loading...")
                    }

                    is LoginState.Success -> {
                        value = "Success!!!"
                        Log.i("Login State->", "Login success")
                        navController.navigate(Routes.UserList.routeName)
                    }

                    is LoginState.Error -> {
                        value = "Error!!!"
                        Log.i("Login State->", "ERROR")
                    }

                }


            }) {
                Text(text = "Login")
            }

            // ShowingResponse(value)
        }
    }
}

@Composable
    fun PreLoginScreen(navController: NavController){
        Column() {


            Button(
                onClick = {
                    navController.navigate(Routes.LoginScreen.routeName)
                },
            ) {
                Text(
                    text = "Login...", modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                )
            }
        }

    }
