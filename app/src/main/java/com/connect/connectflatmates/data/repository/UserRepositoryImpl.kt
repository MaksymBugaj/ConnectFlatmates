package com.connect.connectflatmates.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.connect.connectflatmates.data.db.UserDao
import com.connect.connectflatmates.data.db.entity.UserProfile
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepositoryImpl(private val userDao: UserDao) :
    UserRepository {

    override fun insertUser(userProfile: UserProfile) {
        Completable.fromAction {
            userDao.insert(userProfile)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                Log.d("NOPE","DONE")
            }

    }

    override fun getUsers(): Flowable<List<UserProfile>> {
        return userDao.getAll()
    }

    override fun getUserByLogin(login: String): Single<UserProfile> {
        return userDao.getUser(login)
    }

    override fun getUserById(id: Int): Single<UserProfile> {
        return userDao.getUserById(id)
    }
}