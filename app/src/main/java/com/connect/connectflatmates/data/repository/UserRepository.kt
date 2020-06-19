package com.connect.connectflatmates.data.repository

import androidx.lifecycle.LiveData
import com.connect.connectflatmates.data.db.entity.UserProfile
import io.reactivex.Flowable
import io.reactivex.Maybe

interface UserRepository {

    fun insertUser(userProfile: UserProfile)

    fun getUsers(): Flowable<List<UserProfile>>

    fun getUserByLogin(login: String): Maybe<UserProfile>

    fun getUserById(id: Int): Flowable<UserProfile>
}