package com.connect.connectflatmates.data.db

import androidx.lifecycle.LiveData
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeActivitiesRepositoryImpl(private val homeActivitiesDao: HomeActivitiesDao) : HomeActivitiesRepository {

    override fun insert(homeActivity: HomeActivityEntity) {
        Completable.fromAction {
            homeActivitiesDao.insert(homeActivity)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    override fun delete(homeActivityEntity: HomeActivityEntity) {
        Completable.fromAction {
        homeActivitiesDao.delete(homeActivityEntity)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    override fun getAll(): Flowable<List<HomeActivityEntity>> {
        return homeActivitiesDao.getAll()
    }

    override fun getAssignedHomeActivitiesToUser(userId: String?): Flowable<List<HomeActivityEntity>> {
        return homeActivitiesDao.getAssignedHomeActivitiesToUser(userId)
    }

    override fun getUnassignedHomeActivitiesToUser(): Flowable<List<HomeActivityEntity>> {
        return homeActivitiesDao.getUnassignedHomeActivitiesToUser()
    }

    override fun getAllTest(): Flowable<List<HomeActivityEntity>> {
        return homeActivitiesDao.getAllSingleTest()
    }
}