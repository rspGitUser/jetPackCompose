package com.test.jetpackcompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.jetpackcompose.presentation.screens.LoginScreen
import com.test.jetpackcompose.presentation.screens.PreLoginScreen
import com.test.jetpackcompose.presentation.screens.userList.UserList

@Composable
fun  Navigation(navController:NavHostController)
{

    NavHost(navController = navController , startDestination = Routes.LoginScreen.routeName){


        composable(Routes.PreLoginScreen.routeName){
            PreLoginScreen(navController = navController)
        }
        composable(Routes.LoginScreen.routeName){
            LoginScreen(navController = navController)
            
        }
        composable(Routes.UserList.routeName){
            UserList(navController)
        }
    }
}