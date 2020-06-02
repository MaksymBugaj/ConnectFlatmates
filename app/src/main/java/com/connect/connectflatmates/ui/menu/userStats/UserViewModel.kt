package com.connect.connectflatmates.ui.menu.userStats

import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.repository.UserRepository

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getUserByLogin(login: String) = userRepository.getUserByLogin(login)
}
