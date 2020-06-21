package com.connect.connectflatmates.ui.menu.home.available

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.connect.connectflatmates.R
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import com.connect.connectflatmates.ui.HomeActivitiesAdapter
import com.connect.connectflatmates.ui.menu.home.userActivities.HomeActivitiesViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.unsinged_activities_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UnsingedActivities : Fragment() {

    private val homeActivitiesViewModel by viewModel<HomeActivitiesViewModel>()
    private val unsignedActivitiesViewModel by viewModel<UnsingedActivitiesViewModel>()
    private lateinit var allActivitiesRecyclerView: RecyclerView
    private lateinit var listOfHomeActivities: List<HomeActivityEntity>

    private val compositeDisposable = CompositeDisposable()

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


        compositeDisposable.add(
        homeActivitiesViewModel.getUnassignedHomeActivities().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{listOfActivities ->
                Log.d("NOPE","Unassigned Activities")
            if(listOfActivities.isNotEmpty()) {
                Log.d("NOPE","Unassigned Activities ${listOfActivities.size}")
                listOfHomeActivities = listOfActivities
                (allActivitiesRecyclerView.adapter as HomeActivitiesAdapter).setItems(listOfHomeActivities)
            }

        })
    }

    private fun setRecyclerListener(){
        (allActivitiesRecyclerView.adapter as HomeActivitiesAdapter).setOnClickListener(object : HomeActivitiesAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, view: View) {
                assignUser(position)
                Log.d("NOPE","Unassigned Activities Click: ${listOfHomeActivities[position].name} id: ${listOfHomeActivities[position].id}")
            }
        })
    }

    private fun assignUser(position: Int) {
        val homeActivity = listOfHomeActivities[position].copy(assignedUser = "0")
        unsignedActivitiesViewModel.delete(listOfHomeActivities[position])
        unsignedActivitiesViewModel.assignActivity(homeActivity)

        //todo current user in sessionRepo:::!!!

    }

}
