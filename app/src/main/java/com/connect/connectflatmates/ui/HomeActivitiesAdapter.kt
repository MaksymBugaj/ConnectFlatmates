package com.connect.connectflatmates.ui

import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.connect.connectflatmates.R
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import kotlinx.android.synthetic.main.home_activity_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeActivitiesAdapter : RecyclerView.Adapter<HomeActivitiesAdapter.ViewHolder>() {

    var listOfActivities = ArrayList<HomeActivityEntity>()
    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.home_activity_item, parent, false)
        return ViewHolder(layout, listener)

    }

    override fun getItemCount(): Int {
        return listOfActivities.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItems(listOfActivities[position])

        if (position + 1 == itemCount) {
            // It is the last item of the list

            // Set bottom margin to 72dp
            setBottomMargin(
                holder.itemView,
                (72 * Resources.getSystem().getDisplayMetrics().density).toInt()
            )
        } else {
            // Reset bottom margin back to zero
            setBottomMargin(holder.itemView, 0)
        }
    }

    /**
     * Sets a margin to the bottom of the view.
     *
     * @param view         The view to add the margin to.
     * @param bottomMargin The bottom margin to be added to the view.
     */
    private fun setBottomMargin(view: View, bottomMargin: Int) {
        if (view.layoutParams is MarginLayoutParams) {
            val params = view.layoutParams as MarginLayoutParams
            params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, bottomMargin)
            view.requestLayout()
        }
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun setItems(homeActivities: List<HomeActivityEntity>){
        listOfActivities.clear()
        listOfActivities = homeActivities as ArrayList<HomeActivityEntity>
        Log.d("NOPE","Adapter size ${listOfActivities.size} ")
        notifyDataSetChanged()
    }

    fun removeAt(position: Int){

    }


    inner class ViewHolder(itemView: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        private val homeActivityItemName: TextView = itemView.homeActItem_itemName
        private val homeActivityItemStartDate: TextView = itemView.homeActItem_itemStartDate
        private val homeActivityItemEndDate: TextView = itemView.homeActItem_itemEndDate
        private val homeActivityItemButton: Button = itemView.homeActItem_button

        init {
            itemView.homeActItem_button.setOnClickListener {
                val position = adapterPosition
                listener.onItemClick(position, it)
            }
        }

        fun setItems(homeActivityEntity: HomeActivityEntity) {
            val buttonTextAssign = "ASSIGN"
            val buttonTextDismiss = "COMPLETE"

            homeActivityItemName.text = homeActivityEntity.name
            homeActivityItemStartDate.text = calculateDate((homeActivityEntity.startDate))
            homeActivityItemEndDate.text = calculateDate(homeActivityEntity.endDate)
            homeActivityItemButton.text = if(homeActivityEntity.assignedUser != null) buttonTextDismiss else buttonTextAssign
        }

        private fun calculateDate(date: Long): String{
            val sdf =
                SimpleDateFormat("EEE dd-MM")
            return sdf.format(date)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, view: View)
    }
}