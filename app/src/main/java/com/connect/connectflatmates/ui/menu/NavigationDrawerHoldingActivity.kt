package com.connect.connectflatmates.ui.menu

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.connect.connectflatmates.R
import com.connect.connectflatmates.data.db.entity.UserProfile
import com.connect.connectflatmates.data.repository.SessionRepository
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_navigation_drawer_holding.*
import org.koin.android.ext.android.inject


class NavigationDrawerHoldingActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout
    private lateinit var navController: NavController
    private val sessionRepository by inject<SessionRepository>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer_holding)

        /* val toolbarLayout: Toolbar = toolbar

         setSupportActionBar(toolbarLayout)

         val navigationView = navigation_drawer_view
         navigationView.setNavigationItemSelectedListener(this)
         drawer = drawer_layout
        *//* navController = Navigation.findNavController(this,R.id.fragment_container)
        NavigationUI.setupActionBarWithNavController(this,navController,drawer)
        NavigationUI.setupWithNavController(navigationView,navController)*//*

        val toggle = ActionBarDrawerToggle(this, drawer, toolbarLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()*/

        setupToolbar()
        setupNavigation()
        getCurrentUser()
    }

    private fun setupToolbar() {
        val toolbarLayout: Toolbar = toolbar
        setSupportActionBar(toolbarLayout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun setupNavigation() {
        drawer = drawer_layout
        val navigationView = navigation_drawer_view

        navController = Navigation.findNavController(this, R.id.fragment_container)

        NavigationUI.setupActionBarWithNavController(this, navController, drawer)

        NavigationUI.setupWithNavController(navigationView, navController)

        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.fragment_container),
            drawer
        )
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.userFragment -> {
                navController.navigate(R.id.userFragment)
            }
            R.id.findPeople -> {
                navController.navigate(R.id.findPeople)
            }

            R.id.homeActivities -> {
                navController.navigate(R.id.homeActivities)
            }

            R.id.addActivity -> {
                navController.navigate(R.id.addActivity)
            }

            R.id.unsingedActivities -> {
                navController.navigate(R.id.unsingedActivities)
            }

            R.id.settingsFragment -> {
                navController.navigate(R.id.settingsFragment)
            }

        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun getCurrentUser() {
        val currentUser = sessionRepository.currentUser
        Log.d("NOPE", "Current User = ${currentUser?.name}")
        setNavigationDrawerWithUser(currentUser!!)
    }

    private fun setNavigationDrawerWithUser(userProfile: UserProfile){
        val navigationView = navigation_drawer_view
        val headerView = navigationView.getHeaderView(0)
        val userName = headerView.findViewById(R.id.navHeader_userName) as TextView
        val nameToDisplay = userProfile.name + " " + userProfile.surname
        userName.text = nameToDisplay
        val userEmail = headerView.findViewById(R.id.navHeader_userEmail) as TextView
        val emailToDisplay = userProfile.email
        userEmail.text = emailToDisplay
    }
}