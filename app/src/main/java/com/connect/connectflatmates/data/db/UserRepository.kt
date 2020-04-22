package com.connect.connectflatmates.data.db

import androidx.lifecycle.LiveData
import com.connect.connectflatmates.data.db.entity.UserEntity

interface UserRepository {

    fun insertUser(userEntity: UserEntity)

    fun getUsers(): LiveData<List<UserEntity>>

    fun getUserByLogin(login: String): LiveData<UserEntity>
}