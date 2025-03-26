package com.test.jetpackcompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.test.jetpackcompose.presentation.screens.LoginScreen
import com.test.jetpackcompose.presentation.screens.userList.SingleUserDetailScreen
import com.test.jetpackcompose.presentation.screens.userList.UserListDetailScreen
import com.test.jetpackcompose.presentation.screens.userList.UserListScreenPrepare

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.Login) {

        composable<Routes.Login> {
            LoginScreen() {
                navController.navigate(Routes.UserList) {

                }
            }
        }
        composable<Routes.UserList> {
            UserListScreenPrepare( ) {
                navController.navigate(Routes.UserListDetail)
            }

        }

        composable<Routes.UserListDetail> {
            UserListDetailScreen()
        }


    }
}