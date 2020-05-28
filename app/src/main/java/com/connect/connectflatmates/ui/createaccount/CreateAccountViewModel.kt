package com.connect.connectflatmates.ui.createaccount

import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.db.entity.UserProfile
import com.connect.connectflatmates.data.repository.UserRepository

class CreateAccountViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun insert(userProfile: UserProfile){
        userRepository.insertUser(userProfile)
    }
}
