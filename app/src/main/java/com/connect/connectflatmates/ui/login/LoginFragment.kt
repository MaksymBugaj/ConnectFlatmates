package com.connect.connectflatmates.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

import com.connect.connectflatmates.R
import com.connect.connectflatmates.databinding.LoginFragmentBinding
import com.connect.connectflatmates.state.login.LoginState
import kotlinx.android.synthetic.main.login_fragment.*
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

        /*layoutLogin_createAccountText.setOnClickListener {
            noAccount()
            Log.d("NOPE","NOPE HELP anybpody?. IM STUCKK")
        }*/

        loginViewModel.state.subscribe {
            when(it){
                LoginState.LoginValid -> {
                    login()
                    //todo after login
//                    loginViewModel.setStateToInitial()
                }
                LoginState.NoUser -> {
                    noAccount()
//                    loginViewModel.setStateToInitial()
                    Log.d("NOPE","NOPE HELP MEEEE. IM NoUser")
                }
                //fixme back button on create account need to be pressed twice, and after that, in login screen tapping the create one text field crashes the app
                LoginState.InitialState -> {
                    Log.d("NOPE","NOPE HELP MEEEE. IM InitialState STUCKK")
                }
                LoginState.AccountCreated -> {
                    Log.d("NOPE","acc created")

                }
            }
        }

        /*loginViewModel.loginStatus.observe(this@LoginFragment, Observer { loginState ->
            Log.d("NOPE","Current state $loginState")
            when (loginState) {
                LoginState.LoginValid -> {
                    login()
                    //todo after login 
//                    loginViewModel.setStateToInitial()
                }
                LoginState.NoUser -> {
                    noAccount()
//                    loginViewModel.setStateToInitial()
                    Log.d("NOPE","NOPE HELP MEEEE. IM STUCKK")
                }
                //fixme back button on create account need to be pressed twice, and after that, in login screen tapping the create one text field crashes the app
                LoginState.InitialState -> {
                    Log.d("NOPE","NOPE HELP MEEEE. IM not STUCKK")
                }
                LoginState.AccountCreated -> {
                    Log.d("NOPE","acc created")

                }


            }

        })*/

        /*loginViewModel.loginStatusT.observe(this@LoginFragment, Observer { loginState ->
            Log.d("NOPE","Current state $loginState")
            when (loginState) {
                LoginValid -> {
                    login()
                }
                NoUser -> {
                    noAccount()
                    loginViewModel.setStateToInitial()
                    Log.d("NOPE","NOPE HELP MEEEE. IM STUCKK")
                }
                //fixme back button on create account need to be pressed twice, and after that, in login screen tapping the create one text field crashes the app
                InitialState -> {
                    Log.d("NOPE","NOPE HELP MEEEE. IM not STUCKK")
                }


            }

        })*/

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
        val destination: NavDestination? = findNavController().currentDestination
        if(R.id.loginFragment == destination?.id)
        findNavController().navigate(R.id.action_loginFragment_to_createAccount)
    }

    private fun login(){
        val destination: NavDestination? = findNavController().currentDestination
        if(R.id.loginFragment == destination?.id)
        findNavController().navigate(R.id.action_loginFragment_to_menuFragment)
    }

    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        loginViewModel.onVisible()
    }
}
