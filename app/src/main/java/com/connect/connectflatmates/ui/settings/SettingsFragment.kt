package com.connect.connectflatmates.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.connect.connectflatmates.R
import com.connect.connectflatmates.databinding.SettingsFragmentBinding
import com.connect.connectflatmates.ui.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingsFragment : Fragment() {

    private val settingsViewModel by viewModel<SettingsViewModel>()
    lateinit var navBar: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return SettingsFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = settingsViewModel
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //todo check if there is another option to hide navbar, maybe state?
        navBar = requireActivity().findViewById(R.id.homeActivities_bottomNav)
        navBar.visibility = View.GONE

        settingsViewModel.settingsStatus.observe(viewLifecycleOwner, Observer { settingsStatus ->
            when (settingsStatus) {
                LogoutClick -> startActivity(Intent(requireContext(), MainActivity::class.java))
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        navBar.visibility = View.VISIBLE
    }

}
