package com.connect.connectflatmates.data.repository

import androidx.lifecycle.LiveData
import com.connect.connectflatmates.data.db.entity.UserProfile
import io.reactivex.Flowable

interface UserRepository {

    fun insertUser(userProfile: UserProfile)

    fun getUsers(): Flowable<List<UserProfile>>

    fun getUserByLogin(login: String): LiveData<UserProfile>

    fun getUserById(id: Int): Flowable<UserProfile>
}