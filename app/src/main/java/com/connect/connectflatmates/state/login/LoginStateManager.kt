package com.connect.connectflatmates.state.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LoginStateManager {

    val _currentState: MutableLiveData<LoginState>
    val currentState: LiveData<LoginState>


    fun setState(state: LoginState)
}