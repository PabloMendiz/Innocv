package com.example.innocv.data.remote

import com.example.innocv.data.Model.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface Api {

    @GET("User")
    suspend fun getUserList(): Response<List<User>>

    @GET("User/{id}")
    suspend fun getOneUser(@Path("id") id: Int): Response<User>

    @POST("User")
    suspend fun addUser(@Body user: User): Response<Void>

    @DELETE("User/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Void>

    @PUT("User")
    suspend fun updateUser(@Body user: User): Response<Void>
}

object RetrofitFactory {
    private const val URL = "http://hello-world.innocv.com/api/"

    fun getApi(): Api {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}