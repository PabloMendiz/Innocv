package com.example.innocv.UserList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.innocv.Model.User
import com.example.innocv.R
import com.example.innocv.remote.RemoteRepository

class UserFragment : Fragment(), UserView, RemoteRepository {
    private lateinit var userAdapter: UserAdapter
    private lateinit var remoteRepository: RemoteRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
        val presenter = UserFragPresenter(this, remoteRepository)
        presenter.init()
        userAdapter = UserAdapter {
            val user = it as User

        }
    }

    /*
          UserView Functions
     */
    override fun showEmpty() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showUsers(results: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /*
    Remote Repository funtions
     */
    override suspend fun getUserList(): List<User>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getOneUser(id: Int): User? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun addUser(user: User): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateUser(user: User): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deleteUser(id: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
