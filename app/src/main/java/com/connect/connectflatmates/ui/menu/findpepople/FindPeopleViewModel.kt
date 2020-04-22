package com.connect.connectflatmates.ui.menu.findpepople

import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.db.UserRepository

class FindPeopleViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getAllUser() = userRepository.getUsers()
}
