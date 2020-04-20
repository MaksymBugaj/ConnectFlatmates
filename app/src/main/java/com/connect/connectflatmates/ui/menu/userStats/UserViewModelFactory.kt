package com.connect.connectflatmates.ui.menu.userStats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.connect.connectflatmates.data.UserRepository

class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(userRepository) as T
    }
}