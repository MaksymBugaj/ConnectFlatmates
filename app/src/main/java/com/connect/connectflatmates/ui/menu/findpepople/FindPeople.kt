package com.connect.connectflatmates.ui.menu.findpepople

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.connect.connectflatmates.R
import com.connect.connectflatmates.data.User
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import org.koin.androidx.viewmodel.ext.android.viewModel

class FindPeople : Fragment() {

    private val viewModel by viewModel<FindPeopleViewModel>()


    private lateinit var list : List<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.find_people_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getUsers()
    }

    private fun getUsers() {
        viewModel.getAllUser().observe(viewLifecycleOwner, Observer { usersList ->
            Log.d("NOPE","sizein: ${usersList.size}")

            list = usersList
            for(l in list.indices)
            Log.d("NOPE","id: ${list[l].id}")
        })

    }

    private fun getUserByLogin(login: String){

    }
}
