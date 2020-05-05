package com.connect.connectflatmates.state

sealed class AddActivityState {

    object ChoosingDate : AddActivityState() {
        override fun toString(): String {
            return "ChoosingDateState"
        }
    }

    object DateChosen : AddActivityState() {
        override fun toString(): String {
            return "DateChosenState"
        }
    }
}