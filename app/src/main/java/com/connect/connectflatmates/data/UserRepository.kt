package com.connect.connectflatmates.data

import androidx.lifecycle.LiveData

interface UserRepository {

    fun insertUser(user: User)

    fun getUsers(): LiveData<List<User>>
}