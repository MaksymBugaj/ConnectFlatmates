package com.connect.connectflatmates.state

sealed class StateMachine {

    class LoggingState : StateMachine() {
        override fun toString(): String {
            return "LoggingState"
        }
    }
}