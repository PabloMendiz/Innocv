package com.example.innocv.UserList

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.innocv.Model.User
import com.example.innocv.R
import com.example.innocv.remote.RemoteRepository
import com.example.innocv.remote.RetrofitFactory
import com.example.innocv.remote.RetrofitRemoteRepository
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

        this.userRecyclerView = view.findViewById(R.id.userRecyclerView)
        this.userRecyclerView.layoutManager = LinearLayoutManager(this.context)
        this.userRecyclerView.adapter = this.userAdapter

        this.addUserBtn = view.findViewById(R.id.addBtn)
        addUserBtn.setOnClickListener {
            presenter.addUser()
        }

        this.presenter.init()

        return view
    }
    // UserView Functions
    override fun showUsers(results: List<User>?) {
        results?.let { userAdapter.addUserToList(it) }
    }

    override fun showMsg(txt: String) {
        Toast.makeText(this.context, txt, Toast.LENGTH_SHORT).show()
    }
}
