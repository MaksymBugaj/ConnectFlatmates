package com.connect.connectflatmates.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.connect.connectflatmates.data.repository.SessionRepository

class SettingsViewModel(
    private val sessionRepository: SessionRepository
) : ViewModel() {

    private val _settingsStatus = MutableLiveData<SettingsStatus>()
    val settingsStatus : LiveData<SettingsStatus> = _settingsStatus

    fun logout(){
        sessionRepository.clearCurrentUser()
        _settingsStatus.postValue(LogoutClick)
    }
}

sealed class SettingsStatus
object LogoutClick : SettingsStatus()