package com.connect.connectflatmates.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.connect.connectflatmates.R
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import kotlinx.android.synthetic.main.home_activity_item.view.*

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
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun setItems(homeActivities: List<HomeActivityEntity>){
        listOfActivities = homeActivities as ArrayList<HomeActivityEntity>
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        private val homeActivityItemName: TextView = itemView.homeActItem_itemName
        private val homeActivityItemStartDate: TextView = itemView.homeActItem_itemStartDate
        private val homeActivityItemEndDate: TextView = itemView.homeActItem_itemEndDate

        init {
            itemView.homeActItem_button.setOnClickListener {
                val position = adapterPosition
                listener.onItemClick(position, it)
            }
        }

        fun setItems(homeActivityEntity: HomeActivityEntity) {
            homeActivityItemName.text = homeActivityEntity.name
            homeActivityItemStartDate.text = homeActivityEntity.startDate
            homeActivityItemEndDate.text = homeActivityEntity.endDate
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, view: View)
    }
}