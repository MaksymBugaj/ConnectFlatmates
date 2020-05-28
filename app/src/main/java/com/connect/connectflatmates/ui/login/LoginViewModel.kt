package com.connect.connectflatmates.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.db.entity.UserProfile
import com.connect.connectflatmates.data.repository.SessionRepository
import com.connect.connectflatmates.data.repository.UserRepository

class LoginViewModel(
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {

    fun getUserByLogin(login: String): LiveData<UserProfile> = userRepository.getUserByLogin(login)

    fun getAll() = userRepository.getUsers()
}
