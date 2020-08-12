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
import com.connect.connectflatmates.data.repository.SessionRepository
import com.connect.connectflatmates.ui.HomeActivitiesAdapter
import com.connect.connectflatmates.ui.menu.home.userChores.UserChoresViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.unsinged_activities_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class UnsingedChores : Fragment() {

    private val homeActivitiesViewModel by viewModel<UserChoresViewModel>()
    private val unsignedActivitiesViewModel by viewModel<UnsingedChoresViewModel>()
    private val sessionRepository by inject<SessionRepository>()
    private lateinit var allActivitiesRecyclerView: RecyclerView
    private lateinit var listOfHomeActivities : List<HomeActivityEntity>

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
        homeActivitiesViewModel.getUnassignedHomeActivities()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{listOfActivities ->
                Log.d("NOPE","Unassigned Activities")

                Log.d("NOPE","Unassigned Activities ${listOfActivities.size}")
                listOfHomeActivities = (listOfActivities)
                (allActivitiesRecyclerView.adapter as HomeActivitiesAdapter).setItems(listOfHomeActivities)


        })
    }

    private fun setRecyclerListener(){
        (allActivitiesRecyclerView.adapter as HomeActivitiesAdapter).setOnClickListener(object : HomeActivitiesAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, view: View) {
                Log.d("NOPE","Unassigned Activities Click: ${listOfHomeActivities[position].name} id: ${listOfHomeActivities[position].id}")
                assignUser(position)
            }
        })
    }

    private fun assignUser(position: Int) {
        val userId = sessionRepository.currentUser?.id!!
        val homeActivity = listOfHomeActivities[position].copy(assignedUser = userId.toString())
        unsignedActivitiesViewModel.delete(listOfHomeActivities[position])
        unsignedActivitiesViewModel.assignActivity(homeActivity)
    }

}
