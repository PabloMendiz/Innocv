package com.example.innocv.EditUser

import com.example.innocv.Model.User
import com.example.innocv.remote.RemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditUserPresenter(
    private val view: EditUserView,
    private val remoteRepository: RemoteRepository
) {
    lateinit var user: User
    fun init(user: User) {
        this.user = user
    }

    fun editUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            val isUpdated = remoteRepository.updateUser(user)
            withContext(Dispatchers.Main) {
                if (isUpdated) {
                    return@withContext view.showMsg("User updated")
                } else view.showMsg("Err updating user")
            }
        }
    }

}

interface EditUserView {
    fun showMsg(s: String)

}