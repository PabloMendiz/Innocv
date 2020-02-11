package com.example.innocv.remote

import com.example.innocv.Model.User

interface RemoteRepository {
    suspend fun getUserList(): List<User>
    suspend fun getOneUser(id: Int): User?
    suspend fun addUser(user: User): Boolean
    suspend fun updateUser(user: User): Boolean
    suspend fun deleteUser(id: Int): Boolean
}
