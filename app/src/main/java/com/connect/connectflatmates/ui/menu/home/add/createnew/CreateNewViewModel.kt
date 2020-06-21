package com.connect.connectflatmates.ui.menu.home.add.createnew

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateNewViewModel(

) : ViewModel() {

    val activityToAdd = ObservableField<String>()
    val listOfActivities = MutableLiveData<List<String>>()

}