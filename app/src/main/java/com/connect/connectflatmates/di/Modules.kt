package com.connect.connectflatmates.di

import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.connect.connectflatmates.data.db.*
import com.connect.connectflatmates.data.repository.*
import com.connect.connectflatmates.ui.createaccount.CreateAccountViewModel
import com.connect.connectflatmates.ui.login.LoginViewModel
import com.connect.connectflatmates.ui.menu.MenuViewModel
import com.connect.connectflatmates.ui.menu.findpepople.FindPeopleViewModel
import com.connect.connectflatmates.ui.menu.home.add.AddViewModel
import com.connect.connectflatmates.ui.menu.home.available.UnsingedActivitiesViewModel
import com.connect.connectflatmates.ui.menu.home.userActivities.HomeActivitiesViewModel
import com.connect.connectflatmates.ui.menu.userStats.UserViewModel
import com.connect.connectflatmates.ui.settings.SettingsViewModel
import org.koin.android.ext.koin.androidApplication
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

    single<SessionRepository>{
        SessionRepositoryImpl(userRepository = get(), sharedPreferences = get())
    }
}

val appModule = module {
    single {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val sharedPreferences = EncryptedSharedPreferences
            .create(
                "com.connect.connectflatmates.safepath",
                masterKeyAlias,
                androidApplication(),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        sharedPreferences
    }
}


val viewModelModule = module {
    viewModel {
        CreateAccountViewModel(userRepository = get())
    }

    viewModel {
        LoginViewModel(userRepository = get(), sessionRepository = get())
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