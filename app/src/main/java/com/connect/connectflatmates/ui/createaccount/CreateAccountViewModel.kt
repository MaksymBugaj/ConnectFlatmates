package com.connect.connectflatmates.ui.createaccount

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.connect.connectflatmates.data.User
import com.connect.connectflatmates.data.UserRepositoryImpl

class CreateAccountViewModel(application: Application) : AndroidViewModel(application) {

    private var userRepository: UserRepositoryImpl = UserRepositoryImpl(application)

    fun insert(user: User){
        userRepository.insertUser(user)
    }
}
