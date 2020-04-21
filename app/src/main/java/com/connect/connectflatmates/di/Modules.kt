package com.connect.connectflatmates.di

import androidx.room.Room
import com.connect.connectflatmates.data.UserDatabase
import com.connect.connectflatmates.data.UserRepository
import com.connect.connectflatmates.data.UserRepositoryImpl
import com.connect.connectflatmates.ui.createaccount.CreateAccountViewModel
import com.connect.connectflatmates.ui.login.LoginViewModel
import com.connect.connectflatmates.ui.menu.MenuViewModel
import com.connect.connectflatmates.ui.menu.findpepople.FindPeopleViewModel
import com.connect.connectflatmates.ui.menu.home.HomeActivitiesViewModel
import com.connect.connectflatmates.ui.menu.userStats.UserViewModel
import com.connect.connectflatmates.ui.settings.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            UserDatabase::class.java,
            "connect_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<UserDatabase>().userDao()
    }

    single<UserRepository> {
        UserRepositoryImpl(userDao = get())
    }
}
val viewModelModule = module {
    viewModel {
        CreateAccountViewModel(userRepository = get())
    }

    viewModel {
        LoginViewModel(userRepository = get())
    }

    viewModel {
        FindPeopleViewModel(userRepository = get())
    }

    viewModel {
        HomeActivitiesViewModel()
    }

    viewModel {
        UserViewModel(userRepository = get())
    }

    viewModel {
        MenuViewModel()
    }

    viewModel {
        SettingsViewModel()
    }
}