package com.connect.connectflatmates.ui.createaccount

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.db.entity.UserProfile
import com.connect.connectflatmates.data.repository.UserRepository
import com.connect.connectflatmates.state.login.LoginState
import com.connect.connectflatmates.state.login.LoginStateManager

class CreateAccountViewModel(
    private val userRepository: UserRepository,
    private val loginStateManager: LoginStateManager
) : ViewModel() {

    val name = ObservableField<String>()
    val surname = ObservableField<String>()
    val email = ObservableField<String>()
    val login = ObservableField<String>()
    val password = ObservableField<String>()
    val repeatPassword = ObservableField<String>()

    private val _observeCreate = MutableLiveData<Boolean>()
    val observeCreateAccount: LiveData<Boolean> = _observeCreate


    fun insert(userProfile: UserProfile) {
        userRepository.insertUser(userProfile)
    }


    val loginStatus: LiveData<LoginState>
        get() = loginStateManager.currentState

    private fun setState(state: LoginState) {
        loginStateManager.setState(state)
    }

    fun onCreateClick() {
        Log.d("NOPE","click click:")

        if (!name.get().isNullOrEmpty()
            && !surname.get().isNullOrEmpty()
            && !email.get().isNullOrEmpty()
            && !login.get().isNullOrEmpty()
            && !password.get().isNullOrEmpty()
            && password.get() == repeatPassword.get()
        ) {
            val userProfile = UserProfile(
                name = name.get()!!,
                surname = surname.get()!!,
                email = email.get()!!,
                login = login.get()!!,
                password = password.get()!!
            )
            insert(userProfile)
            setState(LoginState.AccountCreated)
            _observeCreate.value = true
        } else {
            Log.d("NOPE","Problem:")
        }
    }


}
