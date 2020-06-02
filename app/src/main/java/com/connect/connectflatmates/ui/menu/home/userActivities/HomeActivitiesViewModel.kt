package com.connect.connectflatmates.ui.menu.home.userActivities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.db.HomeActivitiesRepository
import com.connect.connectflatmates.data.repository.UserRepository
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import io.reactivex.Flowable

class HomeActivitiesViewModel(
    private val homeActivitiesRepository: HomeActivitiesRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _listOfActivities = MutableLiveData<List<HomeActivityEntity>>()
    val listOfActivities: LiveData<List<HomeActivityEntity>> = _listOfActivities

    fun testGetAll(): Flowable<List<HomeActivityEntity>> = homeActivitiesRepository.getAllTest()

    fun getAll(): Flowable<List<HomeActivityEntity>> = homeActivitiesRepository.getAll()

    fun getAssignedHomeActivities(userId: String?): LiveData<List<HomeActivityEntity>> =
        homeActivitiesRepository.getAssignedHomeActivitiesToUser(userId)

    /*private fun getAllActivites(){
        val activities = homeActivitiesRepository.getAll()
        _listOfActivities.postValue(activities.value)
    }*/

}
