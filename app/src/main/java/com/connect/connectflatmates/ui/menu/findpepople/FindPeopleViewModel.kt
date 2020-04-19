package com.connect.connectflatmates.ui.menu.findpepople

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.User
import com.connect.connectflatmates.data.UserRepository
import com.connect.connectflatmates.data.UserRepositoryImpl

class FindPeopleViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getAllUser() = userRepository.getUsers()
}
