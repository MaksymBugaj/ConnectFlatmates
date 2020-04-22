package com.connect.connectflatmates.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.connect.connectflatmates.data.db.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity)

    @Query("select * from users_table")
    fun getAll(): LiveData<List<UserEntity>>

    @Query("select * from users_table where login =:login")
    fun getUser(login:String):LiveData<UserEntity>
}