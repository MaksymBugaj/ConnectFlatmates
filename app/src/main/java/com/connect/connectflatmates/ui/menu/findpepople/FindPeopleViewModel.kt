package com.connect.connectflatmates.ui.menu.findpepople

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.connect.connectflatmates.data.User
import com.connect.connectflatmates.data.UserRepository

class FindPeopleViewModel(application: Application) : AndroidViewModel(application) {

    private var userRepository: UserRepository = UserRepository(application)

    var getAllUser: LiveData<List<User>> = userRepository.getUsers()
}
