package com.connect.connectflatmates.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.connect.connectflatmates.data.db.entity.UserProfile
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

const val USER_ID_KEY = "user_id"

class SessionRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
    private val userRepository: UserRepository
) : SessionRepository {

    private var user : UserProfile? = null

    override val currentUser: UserProfile?
        get() = user

    override fun loadCurrentUser(): Single<UserProfile?> {
        val userId = sharedPreferences.getInt(USER_ID_KEY, -1)
        return userRepository.getUserById(userId).map {
            user = it
            it
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        /*if(userId != -1) userRepository.getUserById(userId).map {
            Log.d("NOPE","loaded")
            user = it
        }
        return user*/
    }

    override fun clearCurrentUser() {
        Completable.fromAction {
            sharedPreferences.edit().remove(USER_ID_KEY).apply()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                user = null
                Log.d("NOPE","deleted")
            }
    }

    override fun saveUser(userProfile: UserProfile) {
        Completable.fromAction {
            sharedPreferences.edit().putInt(USER_ID_KEY,userProfile.id).apply()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("NOPE","hue got ya")
                this.user = userProfile
            }
    }
}