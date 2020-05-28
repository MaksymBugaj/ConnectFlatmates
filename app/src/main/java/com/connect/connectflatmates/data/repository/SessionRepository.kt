package com.connect.connectflatmates.data.repository

import com.connect.connectflatmates.data.db.entity.UserProfile

interface SessionRepository {
    val currentUser: UserProfile?

    fun loadCurrentUser(): UserProfile
    fun clearCurrentUser()
    fun saveUser(userProfile: UserProfile)
}