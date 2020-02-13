package com.example.innocv.ui.AddUserFragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.innocv.R
import com.example.innocv.data.Model.User
import com.example.innocv.data.remote.RemoteRepository
import com.example.innocv.data.remote.RetrofitFactory
import com.example.innocv.data.remote.RetrofitRemoteRepository


class AddUserDialogFragment(val listener: () -> Unit) : DialogFragment(), AddUserView {
    private lateinit var remoteRepository: RemoteRepository
    private lateinit var presenter: AddUserDialogFragmentPresenter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            this.remoteRepository = RetrofitRemoteRepository(RetrofitFactory.getApi())
            this.presenter = AddUserDialogFragmentPresenter(this, this.remoteRepository)

            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            val view = inflater.inflate(R.layout.add_user_dialog, null)
            builder.setView(view)
            builder.setTitle("Add a user")
            val nameEditTxtField = view.findViewById<EditText>(R.id.userNameInDialogEditTxt)

            builder.setPositiveButton("Add", null)
            builder.setNegativeButton("Cancel") { dialog, which ->
                Toast.makeText(activity, "Op canceled", Toast.LENGTH_SHORT)
                    .show()
            }
            val dialog = builder.create()
            dialog.setOnShowListener {
                val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                positiveButton.setOnClickListener {
                    val nameInTxtField = nameEditTxtField.text.toString()
                    val newUser = User(
                        id,
                        nameInTxtField,
                        "2020-01-22T00:00:00"
                    )
                    this.presenter.addUser(newUser)
                }
            }
            return dialog

        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        listener.invoke()
    }

    override fun showMsg(msg: String) {
        Toast.makeText(activity, "User saved", Toast.LENGTH_SHORT)
            .show()
        dismiss()
    }


}