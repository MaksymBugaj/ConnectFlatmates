package com.connect.connectflatmates.ui.menu.findpepople

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.connect.connectflatmates.data.UserRepository

class FindPeopleViewModelFactory(private val userRepository: UserRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FindPeopleViewModel(userRepository) as T
    }
}