package com.connect.connectflatmates.data

import android.app.Application
import androidx.lifecycle.LiveData
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepository(application: Application) {

    private var userDao: UserDao

    init {
        val database = UserDatabase
            .getInstance(application.applicationContext)

        userDao = database!!.userDao()
    }


    fun insertUser(user: User){
        Completable.fromAction {
            userDao.insert(user)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

    }

    fun getUsers(): LiveData<List<User>>{
        return userDao.getAll()
    }
}