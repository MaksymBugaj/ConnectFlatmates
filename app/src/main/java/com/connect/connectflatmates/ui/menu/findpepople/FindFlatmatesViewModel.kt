package com.connect.connectflatmates.ui.menu.findpepople

import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.repository.UserRepository

class FindFlatmatesViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getAllUser() = userRepository.getUsers()
}
