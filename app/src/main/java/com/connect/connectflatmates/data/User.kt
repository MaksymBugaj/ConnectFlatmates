package com.connect.connectflatmates.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class User(
    val name: String,
    val surname: String,
    val login: String,
    val password: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}