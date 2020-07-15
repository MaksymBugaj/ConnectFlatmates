package com.connect.connectflatmates.data.repository

import com.connect.connectflatmates.data.db.entity.UserProfile
import io.reactivex.Single

interface SessionRepository {
    val currentUser: UserProfile?

    fun loadCurrentUser(): Single<UserProfile?>
    fun clearCurrentUser()
    fun saveUser(userProfile: UserProfile)
}