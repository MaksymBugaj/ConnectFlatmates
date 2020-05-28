package com.connect.connectflatmates.ui.menu.home.userActivities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.connect.connectflatmates.R
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import com.connect.connectflatmates.ui.HomeActivitiesAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.home_activities_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivities : Fragment() {

    private val viewModel by viewModel<HomeActivitiesViewModel>()



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



        assignedActivitiesRecyclerView = homeActivities_yourActivitiesRecycler
        assignedActivitiesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        assignedActivitiesRecyclerView.adapter = HomeActivitiesAdapter()



        (assignedActivitiesRecyclerView.adapter as HomeActivitiesAdapter).setOnClickListener(object : HomeActivitiesAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, view: View) {
                dismissUser()
            }
        })

        viewModel.getAll().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{listOfActivities ->
                if(listOfActivities.isNotEmpty()) {
                    Log.d("NOPE","hue ${listOfActivities[0].assignedUser}")
                    (assignedActivitiesRecyclerView.adapter as HomeActivitiesAdapter).setItems(listOfActivities)
                }

            }

        /*viewModel.getAll().observe(viewLifecycleOwner, Observer {listOfActivities ->
            if(listOfActivities.isNotEmpty()) {
                Log.d("NOPE","hue ${listOfActivities[0].assignedUser}")
                (assignedActivitiesRecyclerView.adapter as HomeActivitiesAdapter).setItems(listOfActivities)
            }

        })*/



    }

    private fun dismissUser() {

    }


}
