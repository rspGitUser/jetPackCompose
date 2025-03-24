package com.test.jetpackcompose.presentation.screens.userList

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.test.jetpackcompose.presentation.navigation.Routes

@Composable
fun UserList(navController: NavController) {
Button(onClick = { navController.navigate(Routes.LoginScreen.routeName)}) {

}
}