package com.example.innocv.UserList

import com.example.innocv.remote.RemoteRepository
import com.example.innocv.remote.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserFragPresenter(
    private val view: UserView,
    private val remoteRepository: RemoteRepository
) {

    fun init() {
        val api = RetrofitFactory.getApi()
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.getUserList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val users = response.body()!!
                    if (users.isEmpty()) {
                        view.showEmpty()
                    }
                    view.showUsers(users)
                } else {
                    view.showError()
                }
            }
        }
    }
}

interface UserView {
    fun showEmpty()
    fun showError()
    fun showUsers(results: Any)
}