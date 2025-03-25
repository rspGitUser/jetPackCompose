package com.test.jetpackcompose.presentation.screens.userList

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.test.jetpackcompose.data.model.userlist.Data
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun UserList(navController: NavController, viewModel: UserListViewModel = hiltViewModel()) {
    // val state by viewModel.loginState.collectAsStateWithLifecycle()

    //val state by viewModel.userListState.collectAsStateWithLifecycle()
    val state by viewModel.userListState.collectAsState()
    val userListData by viewModel.userListData.collectAsState()
    val scope= rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize()) {
        scope.launch {

            viewModel.getUserList()
            delay(1000)
            when (state) {
                is UserListState.Init -> {
                    Log.i("UserListState=>", "Inicializando...")
                }

                is UserListState.Success -> {
                    Log.i("UserListState=>", "Success")

                    userListData.forEach(){
                        Log.i("userListData->", it.email)
                    }
                    navController.popBackStack()




                }

                is UserListState.Loading -> {
                    Log.i("UserListState=>", "Loading...")
                }

                is UserListState.Error -> {
                    Log.i("UserListState=>", "Error...")
                }

            }
        }


    }

}