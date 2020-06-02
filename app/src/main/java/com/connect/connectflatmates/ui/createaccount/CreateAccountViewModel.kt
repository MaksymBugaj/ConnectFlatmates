package com.connect.connectflatmates.ui.createaccount

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.db.entity.UserProfile
import com.connect.connectflatmates.data.repository.UserRepository
import com.connect.connectflatmates.state.login.LoginState
import com.connect.connectflatmates.state.login.LoginStateManager

class CreateAccountViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun insert(userProfile: UserProfile){
        userRepository.insertUser(userProfile)
    }

    private val loginStateManager = LoginStateManager()
    val loginStatus: LiveData<LoginState>
        get() = loginStateManager.currentState

    private fun setState(state: LoginState){
        loginStateManager.setState(state)
    }


}
