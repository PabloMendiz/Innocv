package com.example.innocv.UserList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.innocv.Model.User
import com.example.innocv.R


class UserAdapter(private val presenter: UserFragPresenter) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private var users = listOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position], presenter)
    }

    fun addUserToList(user: List<User>) {
        this.users = user
        notifyDataSetChanged()
    }

    class ViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {
        private val userName = view.findViewById<TextView>(R.id.userNameTxt)
        private val userBirthDate = view.findViewById<TextView>(R.id.userBirthDateTxt)
        private val deleteBtn = view.findViewById<Button>(R.id.btnDelete)
        private val editBtn = view.findViewById<Button>(R.id.btnEdit)

        fun bind(user: User, presenter: UserFragPresenter) {
            userName.text = user.name
            userBirthDate.text = user.birthDate

            deleteBtn.setOnClickListener {
                presenter.deleteUser(user.id)
            }
            editBtn.setOnClickListener {
                presenter.editUser(user.id)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
                return ViewHolder(view)
            }
        }
    }
}
