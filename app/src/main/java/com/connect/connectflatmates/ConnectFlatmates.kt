package com.connect.connectflatmates

import android.app.Application
import com.connect.connectflatmates.data.UserDatabase
import com.connect.connectflatmates.data.UserRepository
import com.connect.connectflatmates.data.UserRepositoryImpl
import com.connect.connectflatmates.ui.createaccount.CreateAccountViewModelFactory
import com.connect.connectflatmates.ui.login.LoginViewModelFactory
import com.connect.connectflatmates.ui.menu.MenuViewModelFactory
import com.connect.connectflatmates.ui.menu.findpepople.FindPeopleViewModelFactory
import com.connect.connectflatmates.ui.menu.userStats.UserViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ConnectFlatmates: Application(), KodeinAware {
    override val kodein= Kodein.lazy {
        import(androidXModule(this@ConnectFlatmates))

        bind() from singleton { UserDatabase(instance()) }
        bind() from singleton { instance<UserDatabase>().userDao() }
        bind<UserRepository>() with singleton { UserRepositoryImpl(instance()) }
        bind() from provider { CreateAccountViewModelFactory(instance()) }
        bind() from provider { FindPeopleViewModelFactory(instance()) }
        bind() from provider { MenuViewModelFactory() }
        bind() from provider { UserViewModelFactory(instance()) }
        bind() from provider { LoginViewModelFactory(instance()) }
    }
}