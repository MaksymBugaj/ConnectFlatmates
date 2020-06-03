package com.connect.connectflatmates.ui.login

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.db.entity.UserProfile
import com.connect.connectflatmates.data.repository.SessionRepository
import com.connect.connectflatmates.data.repository.UserRepository
import com.connect.connectflatmates.state.login.LoginState
import com.connect.connectflatmates.state.login.LoginStateManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository,
    private val loginStateManager: LoginStateManager
) : ViewModel() {

    val login = ObservableField<String>("")
    val password = ObservableField<String>("")


    lateinit var usersList: List<UserProfile>


    val loginStatus: LiveData<LoginState>
    get() = loginStateManager.currentState

    private fun setState(state: LoginState){
        loginStateManager.setState(state)
    }

    private val _loginStatus = MutableLiveData<LoginStateT>()
    val loginStatusT :LiveData<LoginStateT> = _loginStatus
//    fun getUserByLogin(login: String): LiveData<UserProfile> = userRepository.getUserByLogin(login)

    fun onVisible(){
        setStateToInitial()
    }

    fun getAll() {
        userRepository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("NOPE", "We got users ${it.size}")
                if(it!= null)
                usersList = it
            }

    }

    fun onLoginClick() {

        val login = login.get()!!
        val password = password.get()!!

        for (users in usersList) {
            if(login.equals(users.login)){
                if(password.equals(users.password)){
                    setState(LoginState.LoginValid)
                } else {
                    setState(LoginState.WrongPassword)
                }
            }
        }
    }

    fun onNoAccountClick(){
        //_loginStatus.value = NoUser
        setState(LoginState.NoUser)
        Log.d("NOPE", "status?")
    }

   /* fun onLoginClick() {

        val login = login.get()!!
        val password = password.get()!!

        for (users in usersList) {
            if(login.equals(users.login)){
                if(password.equals(users.password)){
                    _loginStatus.value = (LoginValid)
                } else {
                    _loginStatus.value = (WrongPassword)
                }
            }
        }
    }

    fun onNoAccountClick(){
        _loginStatus.value = NoUser
        Log.d("NOPE", "status?")
    }*/

    fun getUserById(id: Int) {
        userRepository.getUserById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("NOPE", "We got user ${it.name}")
            }
    }

    //fixme delete this or change to sth better
    fun setStateToInitial(){
        setState(LoginState.InitialState)
    }
}

sealed class LoginStateT

object InitialState : LoginStateT()
    object CreatingAccount : LoginStateT()
    object LoginValid : LoginStateT()
    object WrongPassword : LoginStateT()
    object NoPassword : LoginStateT()
    object NoUser : LoginStateT()
