package com.example.innocv.ui.UserList

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.innocv.ui.AddUserFragment.AddUserDialogFragment
import com.example.innocv.ui.EditUser.EditUserActivity
import com.example.innocv.data.Model.User
import com.example.innocv.R
import com.example.innocv.data.remote.RemoteRepository
import com.example.innocv.data.remote.RetrofitFactory
import com.example.innocv.data.remote.RetrofitRemoteRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UserFragment : Fragment(), UserView {
    private lateinit var remoteRepository: RemoteRepository
    private lateinit var userAdapter: UserAdapter
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var presenter: UserFragPresenter
    private lateinit var addUserBtn: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        this.remoteRepository = RetrofitRemoteRepository(RetrofitFactory.getApi())
        this.presenter = UserFragPresenter(this, this.remoteRepository)
        this.userAdapter = UserAdapter(this.presenter)
        this.addUserBtn = view.findViewById(R.id.addBtn)

        this.userRecyclerView = view.findViewById(R.id.userRecyclerView)
        this.userRecyclerView.layoutManager = LinearLayoutManager(this.context)
        this.userRecyclerView.adapter = this.userAdapter

        this.presenter.init()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addUserBtn.setOnClickListener {
            AddUserDialogFragment{
                this.presenter.init()
            }.show(childFragmentManager, "addUser")
        }
    }

    override fun onResume() {
        Log.e("FRAGMENT", "onResume")
        super.onResume()
        this.presenter.init()
    }

    /*
     UserView Functions
     */

    // Load the user list on the view
    override fun showUsers(results: List<User>?) {
        results?.let { userAdapter.addUserToList(it) }
    }

    // shows a message on the screen
    override fun showMsg(txt: String) {
        Toast.makeText(this.context, txt, Toast.LENGTH_SHORT).show()
    }

    //Makes the activity open with @param User
    override fun openEditView(user: User) {
        val intent = Intent(this.context, EditUserActivity::class.java)
        intent.putExtra("id", user.id)
        intent.putExtra("name", user.name)
        intent.putExtra("birth", user.birthdate)

        startActivity(intent)
    }
}
