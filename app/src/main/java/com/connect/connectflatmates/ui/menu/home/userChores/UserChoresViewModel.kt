package com.connect.connectflatmates.ui.menu.home.userChores

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.db.HomeActivitiesRepository
import com.connect.connectflatmates.data.repository.UserRepository
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import io.reactivex.Flowable

class UserChoresViewModel(
    private val homeActivitiesRepository: HomeActivitiesRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val groupVisibility = ObservableField(false)

    private val _listOfActivities = MutableLiveData<List<HomeActivityEntity>>()
    val listOfActivities: LiveData<List<HomeActivityEntity>> = _listOfActivities

    fun testGetAll(): Flowable<List<HomeActivityEntity>> = homeActivitiesRepository.getAllTest()

    fun getAll(): Flowable<List<HomeActivityEntity>> = homeActivitiesRepository.getAll()

    fun getAssignedHomeActivities(userId: String?): Flowable<List<HomeActivityEntity>> =
        homeActivitiesRepository.getAssignedHomeActivitiesToUser(userId)

    fun getUnassignedHomeActivities(): Flowable<List<HomeActivityEntity>> =
        homeActivitiesRepository.getUnassignedHomeActivitiesToUser()

    /*private fun getAllActivites(){
        val activities = homeActivitiesRepository.getAll()
        _listOfActivities.postValue(activities.value)
    }*/

    fun assignActivity(homeActivityEntity: HomeActivityEntity){
        homeActivitiesRepository.insert(homeActivityEntity)
    }

    fun delete(homeActivityEntity: HomeActivityEntity){
        homeActivitiesRepository.delete(homeActivityEntity = homeActivityEntity)
    }

}
