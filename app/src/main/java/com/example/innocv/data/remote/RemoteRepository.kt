package com.example.innocv.data.remote

import com.example.innocv.data.Model.User

interface RemoteRepository {
    suspend fun getUserList(): List<User>
    suspend fun getOneUser(id: Int): User?
    suspend fun addUser(user: User): Boolean
    suspend fun updateUser(user: User): Boolean
    suspend fun deleteUser(id: Int): Boolean
}
