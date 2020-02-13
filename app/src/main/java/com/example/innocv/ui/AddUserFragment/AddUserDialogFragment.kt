package com.example.innocv.ui.AddUserFragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.innocv.data.Model.User
import com.example.innocv.R
import com.example.innocv.data.remote.RemoteRepository
import com.example.innocv.data.remote.RetrofitFactory
import com.example.innocv.data.remote.RetrofitRemoteRepository


class AddUserDialogFragment : DialogFragment(), AddUserView {
    private lateinit var remoteRepository: RemoteRepository
    private lateinit var presenter: AddUserDialogFragmentPresenter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            this.remoteRepository = RetrofitRemoteRepository(RetrofitFactory.getApi())
            this.presenter = AddUserDialogFragmentPresenter(this.remoteRepository)

            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            val view = inflater.inflate(R.layout.add_user_dialog, null)
            builder.setView(view)
            builder.setTitle("Add a user")
            val nameEditTxtField = view.findViewById<EditText>(R.id.userNameInDialogEditTxt)

            builder.setPositiveButton("Add") { dialog, which ->
                val nameInTxtField = nameEditTxtField.text.toString()
                val newUser = User(
                    id,
                    nameInTxtField,
                    "2020-01-22T00:00:00"
                )
                this.presenter.addUser(newUser)
            }
            builder.setNegativeButton("Cancel") { dialog, which ->
                Toast.makeText(activity, "Op canceled", Toast.LENGTH_SHORT)
                    .show()
            }
            builder.create()
            builder.show()

        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun showMsg(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}