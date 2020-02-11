package com.example.innocv.User

import com.example.innocv.Model.User
import com.example.innocv.remote.RemoteRepository

class AddUserFragmentPresenter(
    private val view: AddUserView, private val remoteRepository: RemoteRepository
) {
    private lateinit var user: User

    fun init() {
    }
}

interface AddUserView {

}