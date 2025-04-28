package com.example.simplepasswordmanager

data class Account(
    val id: Int,
    val accountName: String,
    val password: String,
    var isExpanded: Boolean = false
)
