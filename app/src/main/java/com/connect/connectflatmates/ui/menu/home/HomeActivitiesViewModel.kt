package com.connect.connectflatmates.ui.menu.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.db.HomeActivitiesRepository
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import io.reactivex.Flowable

class HomeActivitiesViewModel(private val homeActivitiesRepository: HomeActivitiesRepository) : ViewModel() {

    fun testGetAll(): Flowable<List<HomeActivityEntity>> = homeActivitiesRepository.getAllTest()

    fun getAll(): LiveData<List<HomeActivityEntity>> = homeActivitiesRepository.getAll()
}
