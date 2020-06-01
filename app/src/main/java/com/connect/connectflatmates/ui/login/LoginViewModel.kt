package com.connect.connectflatmates.ui.login

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.db.entity.UserProfile
import com.connect.connectflatmates.data.repository.SessionRepository
import com.connect.connectflatmates.data.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {

    val login = ObservableField<String>("")
    val password = ObservableField<String>("")


    lateinit var usersList: List<UserProfile>

    private val _loginStatus = MutableLiveData<LoginState>()
    val loginStatus :LiveData<LoginState> = _loginStatus

    fun getUserByLogin(login: String): LiveData<UserProfile> = userRepository.getUserByLogin(login)

    fun onVisible(){
        _loginStatus.value = InitialState
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

                    _loginStatus.value = LoginValid
                }
                _loginStatus.value = WrongPassword
            }
        }
    }

    fun onNoAccountClick(){
        _loginStatus.value = NoUser
        Log.d("NOPE", "status?")
    }

    fun getUserById(id: Int) {
        userRepository.getUserById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("NOPE", "We got user ${it.name}")
            }
    }
}

sealed class LoginState

object InitialState : LoginState()
object LoginValid : LoginState()
object WrongPassword : LoginState()
object NoPassword : LoginState()
object NoUser : LoginState()
