package com.connect.connectflatmates.ui.menu

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.connect.connectflatmates.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_navigation_drawer_holding.*


class NavigationDrawerHoldingActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer_holding)

        val toolbarLayout: Toolbar = toolbar

        setSupportActionBar(toolbarLayout)

        val navigationView = navigation_drawer_view
        navigationView.setNavigationItemSelectedListener(this)

        navController = Navigation.findNavController(this,R.id.fragment_container)
        NavigationUI.setupActionBarWithNavController(this,navController,drawer)
        NavigationUI.setupWithNavController(navigationView,navController)

       /* drawer = drawer_layout
        val toggle = ActionBarDrawerToggle(this, drawer, toolbarLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()*/
    }

    override fun onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(

            Navigation.findNavController(this, R.id.fragment_container), drawer
        )
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.myProfile ->{

            }
            //todo change the NAME!!!!!!!!!!!!!!!!!!!
            R.id.gloryHole -> {

            }

            R.id.home_Act -> {

            }

            R.id.home_Act_add -> {

            }

            R.id.home_Act_list -> {

            }

        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}