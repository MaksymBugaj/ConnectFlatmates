package com.connect.connectflatmates.ui.menu.home.add

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController

import com.connect.connectflatmates.R
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import com.shrikanthravi.collapsiblecalendarview.data.Day
import kotlinx.android.synthetic.main.add_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class AddChore : Fragment() {

    private val viewModel by viewModel<AddChoresViewModel>()
    private lateinit var startDate: Day
    private lateinit var endDate: Day

    private lateinit var chosenStartDate: String
    private lateinit var chosenEndDate: String

    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var calendar: Calendar

    private val mutableSpinnerItem =  MutableLiveData<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addActivity_spinner.setOnSpinnerItemSelectedListener<String> { position, item ->
            mutableSpinnerItem.value = item
        }

        calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        addActivityLayout_startDate.setEndIconOnClickListener {
            datePickerDialog = DatePickerDialog(requireContext(),
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val dateToDb: Date = Date(year,month,dayOfMonth)
                val date = dayOfMonth.toString() + "/" + (month+1).toString() + "/" + year.toString()
                addActivity_startDate.setText(date)
                addActivity_startDate.isEnabled = false
                chosenStartDate = date
            }, year,month,day)

            datePickerDialog.show()
        }

        addActivityLayout_endDate.setEndIconOnClickListener {
            datePickerDialog = DatePickerDialog(requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val date = dayOfMonth.toString() + "/" + (month+1).toString() + "/" + year.toString()
                    addActivity_endDate.setText(date)
                    addActivity_endDate.isEnabled = false
                    chosenEndDate = date
                }, year,month,day)

            datePickerDialog.show()
        }

        addActivity_createActivity.setOnClickListener {
            Toast.makeText(requireContext(),"To be implemented",Toast.LENGTH_SHORT).show()
        }



//todo wstecz nie finiszuje

        addActivity_addButton.setOnClickListener {
            Log.d("NOPE","HERE text: ${addActivity_addButton.text}")

            val homeActivity = HomeActivityEntity(
                name = mutableSpinnerItem.value!!,
                startDate = chosenStartDate,
                endDate = chosenEndDate,
                assignedUser = null,
                finished = null,
                finishedDate = null
            )

            viewModel.insert(homeActivity)
            findNavController().popBackStack(R.id.homeActivities, false)
        }





       //setCalendarListener()


    }



    /*private fun setCalendarListener(){
        addActivity_calendarView.setCalendarListener(object : CollapsibleCalendar.CalendarListener {
            override fun onClickListener() {
            }

            override fun onDataUpdate() {
            }

            override fun onDayChanged() {
            }

            override fun onDaySelect() {
                addActivity_addButton.setOnClickListener {
                    if (!this@AddActivity::startDate.isInitialized) {
                        startDate = addActivity_calendarView.selectedDay!!
                        val day = startDate.day.toString()
                        val month = (startDate.month + 1).toString()
                        val dayOfMonth = "$day/$month"
                        addActivity_startDate.text = dayOfMonth
                        chosenStartDate = dayOfMonth
                    } else if (!this@AddActivity::endDate.isInitialized) {
                        endDate = addActivity_calendarView.selectedDay!!
                        val day = endDate.day.toString()
                        val month = (endDate.month + 1).toString()
                        val dayOfMonth = "$day/$month"
                        addActivity_endDate.text = dayOfMonth
                        chosenEndDate = dayOfMonth
                    }
                    if (addActivity_addButton.text == "ADD"){

                        Log.d("NOPE","HERE")
                        val homeActivity = HomeActivityEntity(
                            name = mutableSpinnerItem.value!!,
                            startDate = chosenStartDate,
                            endDate = chosenEndDate,
                            assignedUser = "0"
                        )

                        viewModel.insert(homeActivity)
                        findNavController().navigate(R.id.action_addHomeActivity_to_unsingedActivities)
                    }
                    if (this@AddActivity::startDate.isInitialized && this@AddActivity::endDate.isInitialized) {
                        viewModel.setState(AddActivityState.DateChosen)
                    } else {
                        viewModel.setState(AddActivityState.ChoosingDate)
                    }
                }
            }

            override fun onItemClick(v: View) {
            }

            override fun onMonthChange() {
            }

            override fun onWeekChange(position: Int) {
            }

        })
    }*/
}
