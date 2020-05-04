package com.connect.connectflatmates.data.db

import androidx.lifecycle.LiveData
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import io.reactivex.Flowable

interface HomeActivitiesRepository {

    fun insert(homeActivity: HomeActivityEntity)

    fun getAll(): LiveData<List<HomeActivityEntity>>

    fun getHomeActivity(name: String): LiveData<HomeActivityEntity>

    fun getAllTest(): Flowable<List<HomeActivityEntity>>
}