package com.test.jetpackcompose.presentation.navigation

sealed class Routes (val routeName:String){
    object PreLoginScreen: Routes("PreLoginScreen")
    object LoginScreen: Routes("LoginScreen")
    object UserList:Routes("UserList")
}