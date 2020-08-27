package com.connect.connectflatmates.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "home_activities_table")
data class HomeActivityEntity(
    val name: String,
    val startDate: Long,
    val endDate: Long,
    val assignedUser: String?,
    val finished: Boolean?,
    val finishedDate: Long?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}