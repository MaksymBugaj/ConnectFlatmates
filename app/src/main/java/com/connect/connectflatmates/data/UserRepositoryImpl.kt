package com.connect.connectflatmates.data

import androidx.lifecycle.LiveData
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {

    override fun insertUser(user: User) {
        Completable.fromAction {
            userDao.insert(user)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

    }

    override fun getUsers(): LiveData<List<User>> {
        return userDao.getAll()
    }
}