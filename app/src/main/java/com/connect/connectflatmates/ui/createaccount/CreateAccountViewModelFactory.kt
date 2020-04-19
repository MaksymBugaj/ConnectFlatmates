package com.connect.connectflatmates.ui.createaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.connect.connectflatmates.data.UserRepository

class CreateAccountViewModelFactory(private val userRepository: UserRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreateAccountViewModel(userRepository) as T
    }
}