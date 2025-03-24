package com.test.jetpackcompose.data.model.userlist

data class UserList(
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)