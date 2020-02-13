package com.example.innocv.UserList

import com.example.innocv.Model.User
import com.example.innocv.remote.RemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserFragPresenter(
    private val view: UserView,
    private val remoteRepository: RemoteRepository
) {
    private lateinit var user: User

    // Initialize the view when app start loading all users
    fun init() {
        CoroutineScope(Dispatchers.IO).launch {
            val users: List<User>? = remoteRepository.getUserList()
            withContext(Dispatchers.Main) {
                when {
                    users.isNullOrEmpty() -> {
                        view.showMsg("List is empty or null")
                    }
                    else -> view.showUsers(users)
                }
            }
        }
    }

    //Deletes a selected user
    fun deleteUser(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val isDeleted = remoteRepository.deleteUser(id)
            withContext(Dispatchers.Main) {
                if (isDeleted) {
                    view.showMsg("User deleted")
                    init()
                } else view.showMsg("Err deleting user")
            }
        }
    }

    //Sends an order to the view to open the EditUser.Activity
    fun editUser(user: User) {
        view.openEditView(user)
    }

}

interface UserView {
    fun showUsers(results: List<User>?)
    fun showMsg(txt: String)
    fun openEditView(user: User)
}