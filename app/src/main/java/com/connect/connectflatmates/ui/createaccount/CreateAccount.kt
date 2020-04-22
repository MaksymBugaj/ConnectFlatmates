package com.connect.connectflatmates.ui.createaccount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import com.connect.connectflatmates.R
import com.connect.connectflatmates.data.db.entity.UserEntity
import kotlinx.android.synthetic.main.create_account_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateAccount : Fragment() {

    private val viewModel by viewModel<CreateAccountViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_account_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        createAccount_createButton.setOnClickListener { view ->
            val user = UserEntity(
                name = createAccount_name.text.toString(),
                surname = createAccount_surname.text.toString(),
                login = createAccount_login.text.toString(),
                password = createAccount_password.text.toString()
            )

            viewModel.insert(user)
            view.findNavController().navigate(R.id.action_createAccount_to_loginFragment)
        }
    }



}
