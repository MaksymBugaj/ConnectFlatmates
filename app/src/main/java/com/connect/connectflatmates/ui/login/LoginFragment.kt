package com.connect.connectflatmates.ui.login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.connect.connectflatmates.R
import com.connect.connectflatmates.data.User
import com.connect.connectflatmates.databinding.LoginFragmentBinding
import kotlinx.android.synthetic.main.login_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel by viewModel<LoginViewModel>()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LoginFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        login_button.setOnClickListener {
            login()
        }

        create_account.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_loginFragment_to_createAccount)
        }
    }

    private fun login() {
        login_username.text?.let {
            val login = login_username.text.toString()
            viewModel.getUserByLogin(login).observe(viewLifecycleOwner, Observer { user ->
                user?.let {
                    val password = login_password.text.toString()
                    if (user.password == password) {
                        findNavController().navigate(R.id.action_loginFragment_to_menuFragment)
                    } else {
                        showToast("Wrong password")
                    }
                }  ?: kotlin.run{
                    showToast("User does not exist")
                }
            })
        }

    }

    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

}
