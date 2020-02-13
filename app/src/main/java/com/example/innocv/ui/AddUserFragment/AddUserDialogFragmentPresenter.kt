package com.example.innocv.ui.AddUserFragment

import android.app.Dialog
import android.os.Bundle
import com.example.innocv.data.Model.User
import com.example.innocv.data.remote.RemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AddUserDialogFragmentPresenter(
    private val remoteRepository: RemoteRepository
) {
    //Adds a user to the list
    fun addUser(newUser: User) {
        val buildNewUser = User(newUser.id, newUser.name, newUser.birthdate)
        CoroutineScope(Dispatchers.IO).launch {
            remoteRepository.addUser(buildNewUser)
            withContext(Dispatchers.Main) {
                return@withContext
            }
        }
    }
}

interface AddUserView {
    fun onCreateDialog(savedInstanceState: Bundle?): Dialog
    fun showMsg(msg: String)
}