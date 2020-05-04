package com.connect.connectflatmates.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val surname: String,
    val login: String,
    val email: String,
    val password: String
)