package com.connect.connectflatmates.data.repository

import androidx.lifecycle.LiveData
import com.connect.connectflatmates.data.db.entity.UserProfile

interface UserRepository {

    fun insertUser(userProfile: UserProfile)

    fun getUsers(): LiveData<List<UserProfile>>

    fun getUserByLogin(login: String): LiveData<UserProfile>

    fun getUserById(id: Int): UserProfile
}