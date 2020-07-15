package com.connect.connectflatmates.ui.login

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

import com.connect.connectflatmates.R
import com.connect.connectflatmates.databinding.LoginFragmentBinding
import com.connect.connectflatmates.state.login.LoginState
import com.connect.connectflatmates.ui.menu.NavigationDrawerHoldingActivity
import com.fevziomurtekin.customprogress.Type
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.loadingProgressBar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class LoginFragment : Fragment() {

    private val loginViewModel by viewModel<LoginViewModel>()

    private val compositeDisposable = CompositeDisposable()

    var czujka6 = false

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

        compositeDisposable.add(
            loginViewModel.state.subscribe {
                when (it) {
                    LoginState.LoginValid -> {

                        login()
                        layoutLogin_username.isErrorEnabled = false
                        layoutLogin_password.isErrorEnabled = false
                        Log.d("NOPE", "NOPE HELP MEEEE. IM STUCKK")
                    }
                    LoginState.NoUser -> {
                        noAccount()
                        Log.d("NOPE", "NOPE HELP MEEEE. IM NoUser")
                    }
                    LoginState.InitialState -> {
                        Log.d("NOPE", "NOPE HELP MEEEE. IM InitialState STUCKK")
                    }
                    LoginState.AccountCreated -> {
                        Log.d("NOPE", "acc created")

                    }

                    LoginState.WrongPassword, LoginState.UserNotExists -> {
                        layoutLogin_username.error = "Wrong user or password"
                        layoutLogin_password.error = "Wrong user or password"
                    }

                    LoginState.EmptyLogin -> {
                        layoutLogin_username.error = "This field cannot be empty"
                    }

                    LoginState.EmptyPassword -> {
                        layoutLogin_password.error = "This field cannot be empty"
                    }
                    LoginState.UserFromSession -> {
                        if (czujka6) login() else czujka6 = true
                    }
                }
            }
        )

        compositeDisposable.add(
        Observable.interval(45, TimeUnit.MILLISECONDS, Schedulers.newThread())
            .take(50)
            .subscribeBy(
                onNext = {
                    loadingProgressBar.progress = (it.toInt() +1)*2

                },
                onComplete = {
                    loadingProgressBar.progress = 100
                    startAnimation()
                }
            )
        )

        loginViewModel.getAll()
    }

    private fun startAnimation() {
        bookIconImageView.animate().apply {
            x(50f)
            y(100f)
            duration = 1000
        }.setListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {

            }

            override fun onAnimationEnd(p0: Animator?) {
                animationLayout.visibility = View.GONE
                if (czujka6) login() else loginConstraintLayout.visibility = View.VISIBLE
            }

            override fun onAnimationCancel(p0: Animator?) {

            }

            override fun onAnimationStart(p0: Animator?) {

            }
        })
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

    private fun noAccount() {
        val destination: NavDestination? = findNavController().currentDestination
        if (R.id.loginFragment == destination?.id)
            findNavController().navigate(R.id.action_loginFragment_to_createAccount)
    }

    private fun login() {
        Log.d("NOPE", "login")
        startActivity(Intent(context, NavigationDrawerHoldingActivity::class.java))
    }

    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        loginViewModel.onVisible()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
