package com.connect.connectflatmates

import android.app.Application
import com.connect.connectflatmates.di.appModule
import com.connect.connectflatmates.di.databaseModule
import com.connect.connectflatmates.di.viewModelModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ConnectFlatmates : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ConnectFlatmates)
            modules(listOf(viewModelModule, databaseModule, appModule))
        }
        Stetho.initializeWithDefaults(this)
    }
}