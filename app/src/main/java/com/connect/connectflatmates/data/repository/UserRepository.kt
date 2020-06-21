package com.connect.connectflatmates.data.repository

import androidx.lifecycle.LiveData
import com.connect.connectflatmates.data.db.entity.UserProfile
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

interface UserRepository {

    fun insertUser(userProfile: UserProfile)

    fun getUsers(): Flowable<List<UserProfile>>

    fun getUserByLogin(login: String): Single<UserProfile>

    fun getUserById(id: Int): Flowable<UserProfile>
}