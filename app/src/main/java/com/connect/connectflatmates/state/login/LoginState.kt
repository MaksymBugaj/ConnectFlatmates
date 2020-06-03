package com.connect.connectflatmates.state.login



sealed class LoginState {

    object InitialState : LoginState()
    object CreatingAccount : LoginState()
    object LoginValid : LoginState()
    object WrongPassword : LoginState()
    object NoPassword : LoginState()
    object NoUser : LoginState()
    object AccountCreated : LoginState()
}