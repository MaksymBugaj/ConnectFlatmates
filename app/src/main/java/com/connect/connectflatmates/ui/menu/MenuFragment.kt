package com.connect.connectflatmates.ui.menu

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import com.connect.connectflatmates.R
import kotlinx.android.synthetic.main.menu_fragment.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class MenuFragment : Fragment(), KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: MenuViewModelFactory by instance()


    private lateinit var viewModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MenuViewModel::class.java)

        settings.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_menuFragment_to_settingsFragment)
        }

        houseActivities.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_menuFragment_to_homeActivities)
        }

        peopleImage.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_menuFragment_to_findPeople)
        }
    }

}
