package com.connect.connectflatmates.ui.menu.home

import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.db.HomeActivitiesRepository
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity

class AddViewModel(private val homeActivitiesRepository: HomeActivitiesRepository) : ViewModel() {

    fun insert(homeActivity: HomeActivityEntity) = homeActivitiesRepository.insert(homeActivity)

}
