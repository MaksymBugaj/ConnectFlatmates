package com.connect.connectflatmates.data.db

import androidx.lifecycle.LiveData
import com.connect.connectflatmates.data.db.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepositoryImpl(private val userDao: UserDao) :
    UserRepository {

    override fun insertUser(userEntity: UserEntity) {
        Completable.fromAction {
            userDao.insert(userEntity)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

    }

    override fun getUsers(): LiveData<List<UserEntity>> {
        return userDao.getAll()
    }

    override fun getUserByLogin(login: String): LiveData<UserEntity> {
        return userDao.getUser(login)
    }
}