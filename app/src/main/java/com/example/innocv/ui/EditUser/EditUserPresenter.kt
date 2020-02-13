package com.example.innocv.ui.EditUser

import com.example.innocv.data.Model.User

import com.example.innocv.data.remote.RemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditUserPresenter(
    private val view: EditUserView,
    private val remoteRepository: RemoteRepository
) {
    lateinit var user: User
    fun editUser(updatedUser: User) {
        this.user = updatedUser
        CoroutineScope(Dispatchers.IO).launch {
            val isUpdated = remoteRepository.updateUser(user)
            withContext(Dispatchers.Main) {
                when {
                    isUpdated -> view.navigateToMain()
                    else -> view.showMsg("Error updating")
                }
            }
        }
    }
}

interface EditUserView {
    fun showMsg(s: String)
    fun navigateToMain()
}

