package com.connect.connectflatmates.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AddActivityManager {

    private val _addActivityState: MutableLiveData<AddActivityState> =
        MutableLiveData(AddActivityState.ChoosingDate)

    val addActivityState: LiveData<AddActivityState>
        get() = _addActivityState

    fun setAddActivityState(state: AddActivityState){
        _addActivityState.value = state
    }

    fun isAddingStateComplete(): Boolean{
        return _addActivityState.value.toString() == AddActivityState.ChoosingDate.toString()
    }
}