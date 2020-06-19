package com.connect.connectflatmates.state.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoginStateManagerImpl : LoginStateManager{
    override val _currentState: MutableLiveData<LoginState> = MutableLiveData<LoginState>()
    override val currentState: LiveData<LoginState>
        get() = _currentState

    override fun setState(state: LoginState){
        _currentState.value = state
    }
}