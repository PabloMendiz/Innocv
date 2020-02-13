package com.example.innocv.data.remote

import com.example.innocv.data.Model.User


class RetrofitRemoteRepository(private val api: Api) : RemoteRepository {

    //Gets a list of users on the API and returns the response.body if response is successful
    override suspend fun getUserList(): List<User> {
        val response = api.getUserList()
        return if (response.isSuccessful)
            response.body()!!
        else
            return emptyList()
    }

    //Get a user by id and returns the response.body if response is successful
    override suspend fun getOneUser(id: Int): User? {
        val response = api.getOneUser(id)
        return when {
            response.isSuccessful -> response.body()!!
            else -> {
                null
            }
        }
    }

    //Adds a user and returns TRUE if successful
    override suspend fun addUser(user: User): Boolean {
        val response = api.addUser(user)
        return response.isSuccessful
    }

    //Updates a user and returns TRUE if successful
    override suspend fun updateUser(user: User): Boolean {
        val response = api.updateUser(user)
        return response.isSuccessful
    }

    //Delete a user finding it by it and returns TRUE if successful
    override suspend fun deleteUser(id: Int): Boolean {
        val response = api.deleteUser(id)
        return response.isSuccessful
    }
}