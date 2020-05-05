package com.connect.connectflatmates.ui.menu.home.available

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.db.HomeActivitiesRepository
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity

class UnsingedActivitiesViewModel(private val homeActivitiesRepository: HomeActivitiesRepository) : ViewModel() {

    fun getAssignedHomeActivities(userId: String): LiveData<List<HomeActivityEntity>> = homeActivitiesRepository.getAssignedHomeActivitiesToUser(userId)
}
