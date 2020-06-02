package com.connect.connectflatmates.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.connect.connectflatmates.data.db.entity.UserProfile
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userProfile: UserProfile)

    @Query("select * from users_table")
    fun getAll(): Flowable<List<UserProfile>>

    @Query("select * from users_table where login =:login")
    fun getUser(login:String):LiveData<UserProfile>

    @Query("select * from users_table where id =:id")
    fun getUserById(id: Int): Flowable<UserProfile>
}