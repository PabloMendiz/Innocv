package com.example.innocv.ui.EditUser

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.innocv.data.Model.User
import com.example.innocv.R
import com.example.innocv.data.remote.RemoteRepository
import com.example.innocv.data.remote.RetrofitFactory
import com.example.innocv.data.remote.RetrofitRemoteRepository

class EditUserActivity : AppCompatActivity(), EditUserView {
    private lateinit var presenter: EditUserPresenter
    private lateinit var remoteRepository: RemoteRepository

    private lateinit var nameTxt: EditText
    private lateinit var birthTxt: EditText
    private lateinit var btnUpdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user2)

        this.remoteRepository = RetrofitRemoteRepository(RetrofitFactory.getApi())
        this.presenter = EditUserPresenter(this, this.remoteRepository)
        this.btnUpdate = findViewById(R.id.btnUpdate)
        this.nameTxt = findViewById(R.id.updatedNameTxt)
        this.birthTxt = findViewById(R.id.updatedBirthTxt)

        //Get intent values
        val id = intent.extras?.getInt("id")!!
        val name = intent.extras?.getString("name")!!
        val birth = intent.extras?.getString("birth")!!

        //Set text field hints to user current name and birth date
        nameTxt.hint = name
        birthTxt.hint = birth

        //When updateBtn(Aceptar) in clicked
        btnUpdate.setOnClickListener {
            val user = User(id, nameTxt.text.toString(), birthTxt.text.toString())
            presenter.editUser(user)
        }
    }

    // shows a message on the screen
    override fun showMsg(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToMain() {
        finish()
    }
}
