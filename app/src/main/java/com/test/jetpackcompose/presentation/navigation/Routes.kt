package com.test.jetpackcompose.presentation.navigation

import kotlinx.serialization.Serializable
@Serializable
sealed class Routes {
    @Serializable

    object Login:Routes()

    @Serializable
    object UserList:Routes()

    @Serializable
    object UserListDetail:Routes()

    @Serializable
    data class consultinParams(val userId: Int):Routes()
}