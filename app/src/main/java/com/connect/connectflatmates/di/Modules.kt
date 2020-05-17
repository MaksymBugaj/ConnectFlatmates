package com.connect.connectflatmates.di

import androidx.room.Room
import com.connect.connectflatmates.data.db.*
import com.connect.connectflatmates.ui.createaccount.CreateAccountViewModel
import com.connect.connectflatmates.ui.login.LoginViewModel
import com.connect.connectflatmates.ui.menu.MenuViewModel
import com.connect.connectflatmates.ui.menu.findpepople.FindPeopleViewModel
import com.connect.connectflatmates.ui.menu.home.add.AddViewModel
import com.connect.connectflatmates.ui.menu.home.available.UnsingedActivitiesViewModel
import com.connect.connectflatmates.ui.menu.home.userActivities.HomeActivitiesViewModel
import com.connect.connectflatmates.ui.menu.userStats.UserViewModel
import com.connect.connectflatmates.ui.settings.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            ConnectFlatmatesDatabase::class.java,
            "connect_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<ConnectFlatmatesDatabase>().homeActivitiesDao()
    }

    single {
        get<ConnectFlatmatesDatabase>().userDao()
    }

    single<HomeActivitiesRepository>{
        HomeActivitiesRepositoryImpl(homeActivitiesDao = get())
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
        HomeActivitiesViewModel(
            homeActivitiesRepository = get(),
            userRepository = get()
        )
    }

    viewModel {
        UnsingedActivitiesViewModel(
            homeActivitiesRepository = get()
        )
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

    viewModel {
        AddViewModel(homeActivitiesRepository = get())
    }
}