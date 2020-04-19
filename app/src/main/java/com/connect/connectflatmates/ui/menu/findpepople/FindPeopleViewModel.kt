package com.connect.connectflatmates.ui.menu.findpepople

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.connect.connectflatmates.data.User
import com.connect.connectflatmates.data.UserRepositoryImpl

class FindPeopleViewModel(application: Application) : AndroidViewModel(application) {

    private var userRepository: UserRepositoryImpl = UserRepositoryImpl(application)

    var getAllUser: LiveData<List<User>> = userRepository.getUsers()
}
