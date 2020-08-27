package com.connect.connectflatmates.ui.menu.userStats

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.connect.connectflatmates.R
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.user_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserFragment : Fragment() {

    private val userViewModel by viewModel<UserViewModel>()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        compositeDisposable.add(
        userViewModel.getFinishedByUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                Log.d("NOPE", "Error: ${it.message}")
            }
            .subscribe{ listOfActivities ->
                drawChart(listOfActivities)
            }
        )
    }

    private fun drawChart(list: List<HomeActivityEntity>){
        /*val chart: BarChart = userFragment_barChart

        val NoOfEmp = ArrayList<BarEntry>()

        NoOfEmp.add(BarEntry(945f, 0))
        NoOfEmp.add(BarEntry(1040f, 1))
        NoOfEmp.add(BarEntry(1133f, 2))
        NoOfEmp.add(BarEntry(1240f, 3))
        NoOfEmp.add(BarEntry(1369f, 4))
        NoOfEmp.add(BarEntry(1487f, 5))
        NoOfEmp.add(BarEntry(1501f, 6))
        NoOfEmp.add(BarEntry(1645f, 7))
        NoOfEmp.add(BarEntry(1578f, 8))
        NoOfEmp.add(BarEntry(1695f, 9))

        val year = ArrayList<String>()

        year.add("2008")
        year.add("2009")
        year.add("2010")
        year.add("2011")
        year.add("2012")
        year.add("2013")
        year.add("2014")
        year.add("2015")
        year.add("2016")
        year.add("2017")

        val bardataset = BarDataSet(NoOfEmp, "No Of Employee")
        chart.animateY(5000)
        val data = BarData(year, bardataset)
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS)
        chart.data = data
*/
        val pie = AnyChart.line3d()

        val dataSet: MutableList<DataEntry> = ArrayList()
        dataSet.add(ValueDataEntry("John", 10000))
        dataSet.add(ValueDataEntry("Jake", 12000))
        dataSet.add(ValueDataEntry("Peter", 18000))

        pie.data(dataSet)

        pie.animation(true)
        any_chart_view.setChart(pie)
    }

}
