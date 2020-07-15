package com.connect.connectflatmates.state.login



sealed class LoginState {

    object InitialState : LoginState()
    object UserFromSession : LoginState()
    object LoginValid : LoginState()
    object WrongPassword : LoginState()
    object EmptyLogin : LoginState()
    object EmptyPassword : LoginState()
    object UserNotExists : LoginState()
    object NoUser : LoginState()
    object AccountCreated : LoginState()
}