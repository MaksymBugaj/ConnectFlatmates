package com.connect.connectflatmates.ui.createaccount

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.User
import com.connect.connectflatmates.data.UserRepository
import io.reactivex.Flowable

class CreateAccountViewModel(application: Application) : AndroidViewModel(application) {

    private var userRepository: UserRepository = UserRepository(application)

    fun insert(user: User){
        userRepository.insertUser(user)
    }
}
