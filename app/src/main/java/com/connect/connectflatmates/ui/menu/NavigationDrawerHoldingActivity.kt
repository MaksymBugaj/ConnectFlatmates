package com.connect.connectflatmates.ui.menu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.connect.connectflatmates.MainActivity
import com.connect.connectflatmates.R
import com.connect.connectflatmates.data.db.entity.UserProfile
import com.connect.connectflatmates.data.repository.SessionRepository
import com.google.android.material.navigation.NavigationView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_navigation_drawer_holding.*
import org.koin.android.ext.android.inject
import java.util.*
import java.util.concurrent.TimeUnit


class NavigationDrawerHoldingActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout
    private lateinit var navController: NavController
    private val sessionRepository by inject<SessionRepository>()
    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer_holding)

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

        navController = findNavController(this, R.id.fragment_container)

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
            findNavController(this, R.id.fragment_container),
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
            R.id.logout -> {
                sessionRepository.clearCurrentUser()
                navDrawer_group_loading.visibility = View.VISIBLE

                startActivity(Intent(this, MainActivity::class.java))
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

    private fun setNavigationDrawerWithUser(userProfile: UserProfile) {
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