package com.connect.connectflatmates.ui.menu.home

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
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
                R.id.findFlatmates,
                R.id.userFragment,
                R.id.settingsFragment
            )
        )
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        toolbarLayout.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId){
                R.id.menu_settings -> {
                    navController.navigate(R.id.settingsFragment)
                    true
                }
                else -> {
                    false
                }
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    //fixme work this out
   /* override fun onResume() {
        super.onResume()
        homeActivities_bottomNav.visibility = View.VISIBLE
    }*/
}
