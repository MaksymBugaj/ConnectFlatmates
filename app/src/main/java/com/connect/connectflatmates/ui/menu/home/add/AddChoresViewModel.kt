package com.connect.connectflatmates.ui.menu.home.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.db.HomeActivitiesRepository
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import com.connect.connectflatmates.state.AddActivityManager
import com.connect.connectflatmates.state.AddActivityState

class AddChoresViewModel(private val homeActivitiesRepository: HomeActivitiesRepository) : ViewModel() {

    //fixme change it
    fun insert(homeActivity: HomeActivityEntity) = homeActivitiesRepository.insert(homeActivity)


    fun onNoActivityClick(){

    }
}
