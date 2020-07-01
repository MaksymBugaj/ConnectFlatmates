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
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject


class LoginViewModel(
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository,
    private val loginStateManager: LoginStateManager
) : ViewModel() {

    val observableLogin = ObservableField<String>("")
    val observablePassword = ObservableField<String>("")
    val errorLogin = ObservableField<Boolean>(false)
    val errorPassword = ObservableField<Boolean>()
    private val compositeDisposable = CompositeDisposable()


    lateinit var usersList: List<UserProfile>

    val state = PublishSubject.create<LoginState>()



    private fun setState(state: LoginState) {
        loginStateManager.setState(state)
    }

    private val _loginStatus = MutableLiveData<LoginStateT>()
    val loginStatusT: LiveData<LoginStateT> = _loginStatus
//    fun getUserByLogin(login: String): LiveData<UserProfile> = userRepository.getUserByLogin(login)

    fun onVisible() {
        checkIfUserLogged()
        setStateToInitial()
    }

    private fun checkIfUserLogged() {
        val currentUser = sessionRepository.loadCurrentUser()
        if(currentUser == null) {
            Log.d("NOPE","Null user")
        } else {
            Log.d("NOPE","Not null")
            state.onNext(LoginState.LoginValid)
        }
    }

    fun getAll() {
        compositeDisposable.add(
        userRepository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("NOPE", "We got users ${it.size}")
                if (it != null)
                    usersList = it
            }
        )

    }

    fun onLoginClick() {
        val login = observableLogin.get()!!
        if(login.isNullOrEmpty()){
            state.onNext(LoginState.EmptyLogin)
        } else getUserByLogin(login)
        //quickLogin()
    }



    //todo delete after test
    private fun quickLogin(){
        state.onNext(LoginState.LoginValid)
    }

    private fun getUserByLogin(login: String) {
        compositeDisposable.add(
        userRepository.getUserByLogin(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { t: UserProfile? -> performLogin(t!!) },
                { obj: Throwable -> Log.d("NOPE", "UserNoExists")
                    state.onNext(LoginState.UserNotExists)}
            )
        )
    }

    private fun performLogin(userProfile: UserProfile) {
        val password = observablePassword.get()!!
        if (password == userProfile.password) {
            Log.d("NOPE", "NOPE HELP MEEEE. IM loginValid")
            sessionRepository.saveUser(userProfile)
            state.onNext(LoginState.LoginValid)
        } else if(password.isNullOrEmpty()){
            state.onNext(LoginState.EmptyPassword)
        } else {
            Log.d("NOPE", "NOPE HELP MEEEE. IM password bad")
            state.onNext(LoginState.WrongPassword)
            errorLogin.set(true)
        }
    }

    fun onNoAccountClick() {
        setState(LoginState.NoUser)
        state.onNext(LoginState.NoUser)
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


    //fixme delete this or change to sth better
    private fun setStateToInitial() {
        Log.d("NOPE","State Initial")
        state.onNext(LoginState.InitialState)
    }

    override fun onCleared() {
        Log.d("NOPE","Disposable cleared")
        compositeDisposable.clear()
    }
}

sealed class LoginStateT

object InitialState : LoginStateT()
object CreatingAccount : LoginStateT()
object LoginValid : LoginStateT()
object WrongPassword : LoginStateT()
object NoPassword : LoginStateT()
object NoUser : LoginStateT()
