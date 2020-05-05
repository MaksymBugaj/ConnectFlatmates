package com.connect.connectflatmates.ui.menu.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.connect.connectflatmates.R
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import com.connect.connectflatmates.ui.HomeActivitiesAdapter
import io.reactivex.Completable
import kotlinx.android.synthetic.main.home_activities_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivities : Fragment() {

    private val viewModel by viewModel<HomeActivitiesViewModel>()

    private lateinit var allActivitiesRecyclerView: RecyclerView
    private lateinit var listOfHomeActivities: List<HomeActivityEntity>

    private lateinit var assignedActivitiesRecyclerView: RecyclerView
    private lateinit var listOfAssignedHomeActivities: List<HomeActivityEntity>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_activities_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        allActivitiesRecyclerView = homeActivities_allActivitiesRecycler
        allActivitiesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        allActivitiesRecyclerView.adapter = HomeActivitiesAdapter()

        assignedActivitiesRecyclerView = homeActivities_yourActivitiesRecycler
        assignedActivitiesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        assignedActivitiesRecyclerView.adapter = HomeActivitiesAdapter()

        (allActivitiesRecyclerView.adapter as HomeActivitiesAdapter).setOnClickListener(object : HomeActivitiesAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, view: View) {
                Log.d("NOPE","DISMISS ME")
            }
        })

        (assignedActivitiesRecyclerView.adapter as HomeActivitiesAdapter).setOnClickListener(object : HomeActivitiesAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, view: View) {
                Log.d("NOPE","SMISS ME")
            }
        })

        homeActivities_addButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeActivityEntity_to_addActivity)
        }

        viewModel.getAll().observe(viewLifecycleOwner, Observer {listOfActivities ->
            if(listOfActivities.isNotEmpty()) {
                listOfHomeActivities = listOfActivities
                (allActivitiesRecyclerView.adapter as HomeActivitiesAdapter).setItems(listOfHomeActivities)
            }

        })

        viewModel.getAll().observe(viewLifecycleOwner, Observer {listOfActivities ->
            if(listOfActivities.isNotEmpty()) {
                listOfAssignedHomeActivities = listOfActivities
                (assignedActivitiesRecyclerView.adapter as HomeActivitiesAdapter).setItems(listOfAssignedHomeActivities)
            }

        })



    }
}
