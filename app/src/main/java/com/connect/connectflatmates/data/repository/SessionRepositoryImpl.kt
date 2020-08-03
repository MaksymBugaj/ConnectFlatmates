package com.connect.connectflatmates.data.repository

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.connect.connectflatmates.data.db.entity.UserProfile
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

const val USER_ID_KEY = "user_id"

class SessionRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
    private val userRepository: UserRepository
) : SessionRepository {

    private var user : UserProfile? = null

    private val _observableUser = MutableLiveData<UserProfile>()

    override val observableUser: LiveData<UserProfile>
        get() = _observableUser
    

    private val compositeDisposable = CompositeDisposable()

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
            sharedPreferences.edit().remove(USER_ID_KEY).apply()
        user = null
        Log.d("NOPE","user cleared")
    }

    override fun saveUser(userProfile: UserProfile) {
        compositeDisposable.add(Completable.fromAction {
            sharedPreferences.edit().putInt(USER_ID_KEY,userProfile.id).apply()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("NOPE","hue got ya")
                this.user = userProfile
                _observableUser.postValue(userProfile)
                clear()
            }
        )
    }

    private fun clear(){
        Log.d("NOPE","cleared")
        compositeDisposable.clear()
    }


}