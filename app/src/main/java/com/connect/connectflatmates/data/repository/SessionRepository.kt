package com.connect.connectflatmates.data.repository

import com.connect.connectflatmates.data.db.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface SessionRepository {
    val currentUser: UserEntity?

    fun loadCurrentUser(): Flowable<UserEntity>
    fun clearCurrentUser()
    fun saveUser(userEntity: UserEntity)
}