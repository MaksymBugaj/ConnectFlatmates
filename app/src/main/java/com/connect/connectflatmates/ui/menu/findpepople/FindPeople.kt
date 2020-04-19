package com.connect.connectflatmates.ui.menu.findpepople

import android.app.Application
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.connect.connectflatmates.R
import com.connect.connectflatmates.data.User

class FindPeople : Fragment() {

    companion object {
        fun newInstance() = FindPeople()
    }

    private lateinit var viewModel: FindPeopleViewModel
    private lateinit var list : List<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.find_people_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(activity!!.application)
            .create(FindPeopleViewModel::class.java)

        Log.d("NOPE","it: in on create")
        getUser()
    }

    private fun getUser() {
        viewModel.getAllUser.observe(viewLifecycleOwner, Observer { usersList ->
            Log.d("NOPE","sizein: ${usersList.size}")

            list = usersList
            for(l in list.indices)
            Log.d("NOPE","id: ${list[l].id}")
        })

    }
}
