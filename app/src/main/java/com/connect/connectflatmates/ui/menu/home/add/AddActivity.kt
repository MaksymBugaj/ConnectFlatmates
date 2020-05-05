package com.connect.connectflatmates.ui.menu.home.add

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.connect.connectflatmates.R
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import com.connect.connectflatmates.state.AddActivityState
import com.shrikanthravi.collapsiblecalendarview.data.Day
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar
import kotlinx.android.synthetic.main.add_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddActivity : Fragment() {

    private val viewModel by viewModel<AddViewModel>()
    private lateinit var startDate: Day
    private lateinit var endDate: Day

    private lateinit var chosenStartDate: String
    private lateinit var chosenEndDate: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            context!!,
            R.array.homeActivities_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            addActivity_spinner.adapter = adapter
        }

        val dispplayedValues = arrayOf("HIGH", "MEDIUM", "LOW")
        addActivity_numberPicker.displayedValues = dispplayedValues
        addActivity_numberPicker.minValue = 0
        addActivity_numberPicker.maxValue = dispplayedValues.size - 1
        addActivity_numberPicker.wrapSelectorWheel = true

//todo wstecz nie finiszuje

        addActivity_addButton.setOnClickListener {
            Log.d("NOPE","HERE text: ${addActivity_addButton.text}")

        }

       observeButton()

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
                            name = addActivity_spinner.selectedItem.toString(),
                            priority = addActivity_numberPicker.value.toString(),
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

        addActivity_dismissButton.setOnClickListener {
            findNavController().navigate(R.id.action_addHomeActivity_to_myActivities)
        }
    }

    private fun observeButton(){
        viewModel.addActivityState.observe(viewLifecycleOwner, Observer {addActivityState ->
            when(addActivityState){
                is AddActivityState.ChoosingDate -> {
                    addActivity_addButton.text = "CONFIRM"
                }
                is AddActivityState.DateChosen -> {
                    addActivity_addButton.text = "ADD"
                }
            }

        })
    }
}
