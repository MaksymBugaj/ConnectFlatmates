package com.connect.connectflatmates.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.User
import com.connect.connectflatmates.data.UserRepository

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getUserByLogin(login: String): LiveData<User> = userRepository.getUserByLogin(login)

    fun getAll() = userRepository.getUsers()
}
