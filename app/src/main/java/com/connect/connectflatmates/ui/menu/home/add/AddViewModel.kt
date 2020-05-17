package com.connect.connectflatmates.ui.menu.home.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.db.HomeActivitiesRepository
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import com.connect.connectflatmates.state.AddActivityManager
import com.connect.connectflatmates.state.AddActivityState

class AddViewModel(private val homeActivitiesRepository: HomeActivitiesRepository) : ViewModel() {

    fun insert(homeActivity: HomeActivityEntity) = homeActivitiesRepository.insert(homeActivity)

    val addActivityManager = AddActivityManager()

    val addActivityState: LiveData<AddActivityState>
        get() = addActivityManager.addActivityState

    fun setState(state: AddActivityState){
        addActivityManager.setAddActivityState(state)
    }
}
