package com.connect.connectflatmates.ui.menu.userStats

import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.db.HomeActivitiesRepository
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import com.connect.connectflatmates.data.repository.SessionRepository
import com.connect.connectflatmates.data.repository.UserRepository
import io.reactivex.Flowable

class UserViewModel(
    private val homeActivitiesRepository: HomeActivitiesRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {

    fun getFinishedByUser(): Flowable<List<HomeActivityEntity>> =
        homeActivitiesRepository.getAssignedHomeActivitiesToUser(sessionRepository.currentUser?.id.toString(), finished = true)
}
