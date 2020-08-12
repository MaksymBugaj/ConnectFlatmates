package com.connect.connectflatmates.ui.menu.home.available

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.db.HomeActivitiesRepository
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity

class UnsingedChoresViewModel(private val homeActivitiesRepository: HomeActivitiesRepository) : ViewModel() {

//    fun getAssignedHomeActivities(userId: String): LiveData<List<HomeActivityEntity>> = homeActivitiesRepository.getAssignedHomeActivitiesToUser(userId)

    fun assignActivity(homeActivityEntity: HomeActivityEntity){
        homeActivitiesRepository.insert(homeActivityEntity)
    }

    fun delete(homeActivityEntity: HomeActivityEntity){
        homeActivitiesRepository.delete(homeActivityEntity = homeActivityEntity)
    }
}
