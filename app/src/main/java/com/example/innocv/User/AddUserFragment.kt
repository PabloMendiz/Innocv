package com.example.innocv.User

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.innocv.R
import com.example.innocv.remote.RemoteRepository
import com.example.innocv.remote.RetrofitFactory
import com.example.innocv.remote.RetrofitRemoteRepository

class AddUserFragment : Fragment(), AddUserView {
    private lateinit var presenter: AddUserFragmentPresenter
    private lateinit var remoteRepository: RemoteRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.remoteRepository = RetrofitRemoteRepository(RetrofitFactory.getApi())
        presenter = AddUserFragmentPresenter(this, remoteRepository)
        presenter.init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_user2, container, false)
    }


}
