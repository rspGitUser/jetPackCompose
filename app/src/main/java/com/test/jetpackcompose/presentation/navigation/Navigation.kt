package com.test.jetpackcompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.jetpackcompose.presentation.screens.LoginScreen
import com.test.jetpackcompose.presentation.screens.userList.UserList
import com.test.jetpackcompose.presentation.screens.userList.UserListDetailScreen

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = LoginScreen) {
/*
    composable<Home> {
            HomeScreen { name -> navController.navigate(Detail(name = name)) }
        }

 */
        composable<LoginScreen> {
            com.test.jetpackcompose.presentation.screens.LoginScreen {
                navController.navigate(UserList)
            }
        }
        composable<UserList> {
            com.test.jetpackcompose.presentation.screens.userList.UserList{navController.navigate(UserListDetail)}
            //          HomeScreen { name -> navController.navigate(Detail(name = name)) }
        }

        composable<UserListDetail> {
            UserListDetailScreen()
        }
    }
}