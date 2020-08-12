package com.connect.connectflatmates.ui.menu.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.connect.connectflatmates.R
import kotlinx.android.synthetic.main.activity_bottom_nav_holder.*

class BottomNavHolderActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav_holder)

        navController = Navigation.findNavController(this, R.id.homeActivities_navHost)

        NavigationUI.setupWithNavController(topAppBar, navController)

        homeActivities_bottomNav.setupWithNavController(navController)
    }
}
