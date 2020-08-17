package com.connect.connectflatmates.ui.menu.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.connect.connectflatmates.R
import kotlinx.android.synthetic.main.activity_bottom_nav_holder.*
import kotlinx.android.synthetic.main.activity_navigation_drawer_holding.*

class BottomNavHolderActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav_holder)

        navController = Navigation.findNavController(this, R.id.homeActivities_navHost)

        setupToolbar()

        homeActivities_bottomNav.setupWithNavController(navController)
    }


    private fun setupToolbar() {
        val toolbarLayout: Toolbar = topAppBar
        setSupportActionBar(toolbarLayout)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.myActivities,
                R.id.addHomeActivity,
                R.id.unsingedActivities,
                R.id.findFlatmates
            )
        )
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }
}
