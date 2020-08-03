package com.connect.connectflatmates.data.repository

import androidx.lifecycle.LiveData
import com.connect.connectflatmates.data.db.entity.UserProfile
import io.reactivex.Observable
import io.reactivex.Single

interface SessionRepository {
    val currentUser: UserProfile?
    val observableUser: LiveData<UserProfile>

    fun loadCurrentUser(): Single<UserProfile?>
    fun clearCurrentUser()
    fun saveUser(userProfile: UserProfile)
}