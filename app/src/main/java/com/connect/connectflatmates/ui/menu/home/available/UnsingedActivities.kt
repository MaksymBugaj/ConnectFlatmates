package com.connect.connectflatmates.ui.menu.home.available

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
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
import com.connect.connectflatmates.ui.menu.home.userActivities.HomeActivitiesViewModel
import kotlinx.android.synthetic.main.unsinged_activities_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UnsingedActivities : Fragment() {

    private val viewModel by viewModel<HomeActivitiesViewModel>()
    private lateinit var allActivitiesRecyclerView: RecyclerView
    private lateinit var listOfHomeActivities: List<HomeActivityEntity>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.unsinged_activities_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpRecycler()
        setRecyclerListener()
    }

    private fun setUpRecycler(){
        allActivitiesRecyclerView = homeActivities_allActivitiesRecycler
        allActivitiesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        allActivitiesRecyclerView.adapter = HomeActivitiesAdapter()

        val unsignedUser = "0"
        viewModel.getAssignedHomeActivities(unsignedUser).observe(viewLifecycleOwner, Observer {listOfActivities ->
            if(listOfActivities.isNotEmpty()) {
                listOfHomeActivities = listOfActivities
                (allActivitiesRecyclerView.adapter as HomeActivitiesAdapter).setItems(listOfHomeActivities)
            }

        })
    }

    private fun setRecyclerListener(){
        (allActivitiesRecyclerView.adapter as HomeActivitiesAdapter).setOnClickListener(object : HomeActivitiesAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, view: View) {
                assignUser()
            }
        })
    }

    private fun assignUser() {

    }

}
