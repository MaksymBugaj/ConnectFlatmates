package com.connect.connectflatmates.ui.menu.home

sealed class HomeState {

    object HomeActivityAcquired : HomeState() {
        override fun toString(): String {
            return "HomeActivityAcquired"
        }
    }

    class HomeActivityDismissed: HomeState(){
        override fun toString(): String {
            return "HomeActivityDismissed"
        }
    }
}