package com.connect.connectflatmates.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import com.connect.connectflatmates.data.db.entity.UserProfile

@Database(
    entities = [
        UserProfile::class,
        HomeActivityEntity::class
    ],
    version = 6
)
abstract class ConnectFlatmatesDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun homeActivitiesDao(): HomeActivitiesDao
}

