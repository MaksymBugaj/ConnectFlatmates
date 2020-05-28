package com.connect.connectflatmates.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.db.entity.UserEntity
import com.connect.connectflatmates.data.repository.UserRepository

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getUserByLogin(login: String): LiveData<UserEntity> = userRepository.getUserByLogin(login)

    fun getAll() = userRepository.getUsers()
}
