package com.connect.connectflatmates.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.connect.connectflatmates.data.db.UserDao
import com.connect.connectflatmates.data.db.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SessionRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
    private val userRepository: UserRepository
) : SessionRepository {

    private var user : UserEntity? = null

    companion object {
        val USER_ID_KEY = "user_id"
    }



    override val currentUser: UserEntity?
        get() = user

    override fun loadCurrentUser(): Flowable<UserEntity> {
        val user
    }

    override fun clearCurrentUser() {
        Completable.fromAction {
            sharedPreferences.edit().remove(USER_ID_KEY).apply()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("NOPE","deleted")
            }
    }

    override fun saveUser(userEntity: UserEntity) {
        Completable.fromAction {
            sharedPreferences.edit().putString(USER_ID_KEY,userEntity.id).apply()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("NOPE","hue got ya")
                this.user = userEntity
            }
    }
}