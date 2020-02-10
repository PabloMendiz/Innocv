package com.example.innocv.UserList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.innocv.Model.User
import com.example.innocv.R


class UserAdapter(private val listener: (Any) -> Unit) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var users = listOf<Any>()

    fun addUser(newUser: List<Any>) {
        this.users = newUser
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position], listener)
    }

    class ViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {
        private val userName = view.findViewById<TextView>(R.id.userName)
        private val userBirthDate = view.findViewById<TextView>(R.id.userBirthDate)


        fun bind(user: Any, listener: (Any) -> Unit) {
            if (user is User) {
                userName.text = user.name
                userBirthDate.text = user.birthDate
            } else
                error(message = "error")
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
