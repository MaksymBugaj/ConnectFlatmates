package com.connect.connectflatmates.ui.login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import com.connect.connectflatmates.R
import com.connect.connectflatmates.databinding.LoginFragmentBinding
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

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
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        login_button.setOnClickListener {view ->
            view.findNavController().navigate(R.id.action_loginFragment_to_menuFragment)

        }

        create_account.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_loginFragment_to_createAccount)
        }
    }

}
