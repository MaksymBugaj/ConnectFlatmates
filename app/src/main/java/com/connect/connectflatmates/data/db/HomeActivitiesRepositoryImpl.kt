package com.connect.connectflatmates.data.db

import androidx.lifecycle.LiveData
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import io.reactivex.Completable
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

    override fun getAll(): LiveData<List<HomeActivityEntity>> {
        return homeActivitiesDao.getAll()
    }

    override fun getHomeActivity(name: String): LiveData<HomeActivityEntity> {
        return homeActivitiesDao.getHomeActivity(name)
    }
}