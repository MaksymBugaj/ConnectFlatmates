package com.connect.connectflatmates.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("select * from users_table")
    fun getAll(): LiveData<List<User>>

    @Query("select * from users_table where login =:login")
    fun getUser(login:String):LiveData<User>
}