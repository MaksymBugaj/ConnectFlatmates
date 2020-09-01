package com.connect.connectflatmates.ui.menu.home.userChores

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import com.connect.connectflatmates.data.repository.SessionRepository
import com.connect.connectflatmates.databinding.UserChoresFragmentBinding
import com.connect.connectflatmates.ui.HomeActivitiesAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.user_chores_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class UserChores : Fragment() {

    private val userChoresViewModel by viewModel<UserChoresViewModel>()

    private val sessionRepository by inject<SessionRepository>()
    private val compositeDisposable = CompositeDisposable()

    private lateinit var assignedActivitiesRecyclerView: RecyclerView
    private lateinit var listOfAssignedHomeActivities: List<HomeActivityEntity>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return UserChoresFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = userChoresViewModel
            lifecycleOwner = viewLifecycleOwner
        }.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        assignedActivitiesRecyclerView = userChores_yourActivitiesRecycler
        assignedActivitiesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        assignedActivitiesRecyclerView.adapter = HomeActivitiesAdapter()



        (assignedActivitiesRecyclerView.adapter as HomeActivitiesAdapter).setOnClickListener(object :
            HomeActivitiesAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, view: View) {
                completeTask(position)
            }
        })
        val userId = sessionRepository.currentUser?.id!!
        compositeDisposable.add(
            userChoresViewModel.getAssignedHomeActivities(userId.toString(), finished = false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.d("NOPE", "Error: ${it.message}")
                }
                .subscribe { listOfActivities ->

                    Log.d("NOPE", "hue assinged size ${listOfActivities.size}")
                    if(listOfActivities.isNotEmpty()) {
                        userChores_groupLoading.visibility = View.INVISIBLE
                    } else userChores_groupLoading.visibility = View.VISIBLE
                    userChores_groupLoading.invalidate()
                    Log.d("NOPE", "czujka ${listOfActivities.size} visibility: ${userChores_groupLoading.visibility}")

                    listOfAssignedHomeActivities = listOfActivities
                    (assignedActivitiesRecyclerView.adapter as HomeActivitiesAdapter).setItems(
                        listOfActivities
                    )
                }
        )

        /*viewModel.getAll().observe(viewLifecycleOwner, Observer {listOfActivities ->
            if(listOfActivities.isNotEmpty()) {
                Log.d("NOPE","hue ${listOfActivities[0].assignedUser}")
                (assignedActivitiesRecyclerView.adapter as HomeActivitiesAdapter).setItems(listOfActivities)
            }

        })*/




    }

    private fun completeTask(position: Int) {
        /*val str_date = "13-09-2011"
        val sdf =
            SimpleDateFormat("dd-MM-yyyy")
        val currentDateAndTime = sdf.format(Date()) as Date
        val formatter: DateFormat = SimpleDateFormat("dd-MM-yyyy")
        val date: Date = formatter.parse(str_date) as Date*/

        val homeActivity = listOfAssignedHomeActivities[position].copy(finished = true, finishedDate = calculateDate())
        userChoresViewModel.delete(listOfAssignedHomeActivities[position])
        userChoresViewModel.assignActivity(homeActivity)

    }

    private fun calculateDate(): Long{
        val calendar = Calendar.getInstance()
        val currentDate =
            DateFormat.getDateInstance(DateFormat.YEAR_FIELD, Locale.ENGLISH)
                .format(calendar.time)

        //fixme go for current date
        /*val sdf =
            SimpleDateFormat("dd-MM-yyyy")
        val currentDateandTime = sdf.format(Date()) as Date*/

        /*val c = Calendar.getInstance()
        System.out.println("Current time => " + c.time)

        val df = SimpleDateFormat("yyyy-MM-dd")
        val formattedDate = df.format(c.time)*/

        val date = Date()

        System.out.println(date)

        val msec = date.time
        Log.d("NOPE","dateL:  + $currentDate $msec")
        return msec
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }
}
