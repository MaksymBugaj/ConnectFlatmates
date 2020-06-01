package com.connect.connectflatmates.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.connect.connectflatmates.R
import com.connect.connectflatmates.databinding.LoginFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val loginViewModel by viewModel<LoginViewModel>()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LoginFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = loginViewModel
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        loginViewModel.loginStatus.observe(this@LoginFragment, Observer { loginState ->
            when (loginState) {
                LoginValid -> {
                    login()
                }
                NoUser -> {
                    noAccount()
                }
            }

        })

        loginViewModel.getAll()
    }

    /*private fun assertPassword() {
        login_username.text?.let {
            val login = login_username.text.toString()
            viewModel.getUserByLogin(login).observe(viewLifecycleOwner, Observer { user ->
                user?.let {
                    val password = login_password.text.toString()
                    if (user.password == password) {
                        linearLayout.visibility = View.GONE
                        progress_bar.settype(Type.INTERWIND)
                        progress_bar.setdurationTime(100)
                        progress_bar.show()
                        Observable.just(1).delay(5, TimeUnit.SECONDS).subscribe{
                            login()
                            Log.d("NOPE","NOPE HELP MEEEE. IM STUCKK")
                        }
                    } else {
                        showToast("Wrong password")
                    }
                }  ?: kotlin.run{
                    showToast("User does not exist")
                }
            })
        }

    }*/

    private fun noAccount(){
        findNavController().navigate(R.id.action_loginFragment_to_createAccount)
    }

    private fun login(){
        findNavController().navigate(R.id.action_loginFragment_to_menuFragment)
    }

    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

}
