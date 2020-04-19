package com.connect.connectflatmates.ui.menu.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.connect.connectflatmates.R

class HomeActivities : Fragment() {

    companion object {
        fun newInstance() = HomeActivities()
    }

    private lateinit var viewModel: HomeActivitiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_activities_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeActivitiesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
