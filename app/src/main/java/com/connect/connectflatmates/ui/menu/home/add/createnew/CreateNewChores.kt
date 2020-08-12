package com.connect.connectflatmates.ui.menu.home.add.createnew

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.connect.connectflatmates.R

class CreateNewChores : Fragment() {



    private lateinit var choresViewModel: CreateNewChoresViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_new_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        choresViewModel = ViewModelProviders.of(this).get(CreateNewChoresViewModel::class.java)
        // TODO: Use the ViewModel
    }

}