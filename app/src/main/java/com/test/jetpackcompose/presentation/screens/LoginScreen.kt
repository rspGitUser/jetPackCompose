package com.test.jetpackcompose.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.test.jetpackcompose.R
import com.test.jetpackcompose.presentation.components.LoginInputText

import kotlinx.coroutines.Delay
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Route

@OptIn(InternalCoroutinesApi::class)
@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel(),NavigateTo:()->Unit) {

    var email by remember { mutableStateOf("eve.holt@reqres.in") }
    var password by remember { mutableStateOf("cityslicka") }
    val scope= rememberCoroutineScope()
    val attemps by remember {
        mutableStateOf(1)
    }

    val state by viewModel.loginState.collectAsState()

    var value by remember {
        mutableStateOf("")
    }
    Image(
        painter = painterResource(id = R.drawable.lgbackground),
        contentDescription = "Login Background",
        modifier = Modifier
            .fillMaxSize()
            .blur(radius = 10.dp), contentScale = ContentScale.Crop
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 8.dp, start = 8.dp), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier

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
                scope.launch {
                    viewModel.login(email, password)
                    delay(1000)
                    when (state) {

                        is LoginState.Init -> {
                            Log.i("Login State->", "IDLE")

                        }

                        is LoginState.Loading -> {
                            value = LoginState.Loading.toString()
                            Log.i("Login State->", "Navigation Loading...")
                        }

                        is LoginState.Success -> {
                            value = "Success!!!"
                            Log.i("Login State->", "Login success")
                        NavigateTo()

                        }

                        is LoginState.Error -> {
                            value = "Error!!!"
                            Log.i("Login State->", "ERROR")
                        }

                    }

                }
            }) {
                Text(text = "Login")
            }

            // ShowingResponse(value)
        }
    }
}


