package com.connect.connectflatmates.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.connect.connectflatmates.data.db.entity.HomeActivityEntity
import com.connect.connectflatmates.data.db.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        HomeActivityEntity::class
    ],
    version = 1
)
abstract class ConnectFlatmatesDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun homeActivitiesDao(): HomeActivitiesDao
}

