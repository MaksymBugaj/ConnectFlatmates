package com.connect.connectflatmates.ui.createaccount

import android.app.Application
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.connect.connectflatmates.R
import com.connect.connectflatmates.data.User
import kotlinx.android.synthetic.main.create_account_fragment.*

class CreateAccount : Fragment() {

    companion object {
        fun newInstance() = CreateAccount()
    }

    private lateinit var viewModel: CreateAccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_account_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(activity!!.application)
            .create(CreateAccountViewModel::class.java)


        createAccount_createButton.setOnClickListener { view ->
            val user = User(
                name = createAccount_name.toString(),
                surname = createAccount_surname.toString(),
                login = createAccount_login.toString(),
                password = createAccount_password.toString()
            )

            viewModel.insert(user)
            view.findNavController().navigate(R.id.action_createAccount_to_loginFragment)
        }
    }



}
