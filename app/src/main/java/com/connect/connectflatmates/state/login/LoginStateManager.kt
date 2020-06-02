package com.connect.connectflatmates.state.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoginStateManager {

    private val _currentState: MutableLiveData<LoginState> = MutableLiveData<LoginState>()
    val currentState: LiveData<LoginState>
        get() = _currentState

    fun setState(state: LoginState){
        _currentState.value = state
    }
}