package com.example.innocv.Model

data class User(
    val id: Int,
    val name: String,
    val birthDate: String
)

data class UserResults(val results: List<User>)
