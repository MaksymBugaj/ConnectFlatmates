package com.connect.connectflatmates.ui.menu.findpepople

import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.repository.UserRepository

class FindPeopleViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getAllUser() = userRepository.getUsers()
}
