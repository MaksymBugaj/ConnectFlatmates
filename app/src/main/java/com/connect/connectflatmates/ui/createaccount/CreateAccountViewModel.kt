package com.connect.connectflatmates.ui.createaccount

import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.User
import com.connect.connectflatmates.data.UserRepository

class CreateAccountViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun insert(user: User){
        userRepository.insertUser(user)
    }
}
