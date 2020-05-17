package com.connect.connectflatmates.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import io.reactivex.Flowable

@Dao
interface HomeActivitiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(homeActivityEntity: HomeActivityEntity)

    @Query("select * from home_activities_table")
    fun getAll(): LiveData<List<HomeActivityEntity>>

    @Query("select * from home_activities_table where assignedUser =:userId")
    fun getAssignedHomeActivitiesToUser(userId: String?): LiveData<List<HomeActivityEntity>>

    @Query("select * from home_activities_table")
    fun getAllSingleTest(): Flowable<List<HomeActivityEntity>>
}