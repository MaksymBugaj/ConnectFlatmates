package com.connect.connectflatmates.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import io.reactivex.Flowable

@Dao
interface HomeActivitiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(homeActivityEntity: HomeActivityEntity)

    @Delete
    fun delete(homeActivityEntity: HomeActivityEntity)

    @Query("select * from home_activities_table")
    fun getAll(): Flowable<List<HomeActivityEntity>>

    @Query("select * from home_activities_table where assignedUser =:userId")
    fun getAssignedHomeActivitiesToUser(userId: String?): Flowable<List<HomeActivityEntity>>

    @Query("select * from home_activities_table where assignedUser is null")
    fun getUnassignedHomeActivitiesToUser(): Flowable<List<HomeActivityEntity>>

    @Query("select * from home_activities_table")
    fun getAllSingleTest(): Flowable<List<HomeActivityEntity>>
}