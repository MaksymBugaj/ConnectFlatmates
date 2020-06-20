package com.connect.connectflatmates.data.db

import androidx.lifecycle.LiveData
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import io.reactivex.Flowable

interface HomeActivitiesRepository {

    fun insert(homeActivity: HomeActivityEntity)

    fun delete(homeActivityEntity: HomeActivityEntity)

    fun getAll(): Flowable<List<HomeActivityEntity>>

    fun getAssignedHomeActivitiesToUser(userId: String?): Flowable<List<HomeActivityEntity>>

    fun getUnassignedHomeActivitiesToUser(): Flowable<List<HomeActivityEntity>>

    fun getAllTest(): Flowable<List<HomeActivityEntity>>
}