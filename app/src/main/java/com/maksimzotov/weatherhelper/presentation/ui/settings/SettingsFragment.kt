package com.maksimzotov.weatherhelper.presentation.ui.settings

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.maksimzotov.weatherhelper.databinding.SettingsFragmentBinding
import com.maksimzotov.weatherhelper.presentation.main.activity.MainActivityViewModel
import com.maksimzotov.weatherhelper.presentation.main.base.TopLevelFragment

class SettingsFragment
    : TopLevelFragment<SettingsFragmentBinding>(SettingsFragmentBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

        binding.apply {
            switchDarkTheme.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    viewModel.switchToDarkTheme()
                } else {
                    viewModel.switchToLightTheme()
                }
            }
            radioGroupBottomNav.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == bottomNavOn.id) {
                    viewModel.showBottomNavigation()
                } else {
                    viewModel.hideBottomNavigation()
                }
            }
        }

        val activityViewModel =
            ViewModelProvider(requireActivity()) // passing the activity in order to show
                .get(MainActivityViewModel::class.java) // the settings state immediately

        binding.apply {
            activityViewModel.bottomNavigation.observe(viewLifecycleOwner, { bottomNavigation ->
                if (bottomNavigation != null && !bottomNavigation.isAble) {
                    radioGroupBottomNav.check(bottomNavOff.id)
                } else {
                    radioGroupBottomNav.check(bottomNavOn.id)
                }
                radioGroupBottomNav.jumpDrawablesToCurrentState()
            })
            activityViewModel.darkTheme.observe(viewLifecycleOwner, { darkTheme ->
                switchDarkTheme.isChecked = darkTheme != null && darkTheme.isAble
                switchDarkTheme.jumpDrawablesToCurrentState()
            })
        }
    }
}