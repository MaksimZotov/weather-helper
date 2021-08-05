package com.maksimzotov.weatherhelper.presentation.ui.settings

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.maksimzotov.weatherhelper.databinding.SettingsFragmentBinding
import com.maksimzotov.weatherhelper.presentation.main.activity.SettingsSharedViewModel
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

        val settingsSharedViewModel =
            ViewModelProvider(requireActivity())
                .get(SettingsSharedViewModel::class.java)

        binding.apply {
            val bottomNavigation = settingsSharedViewModel.bottomNavigation.value
            if (bottomNavigation != null && !bottomNavigation.isAble) {
                radioGroupBottomNav.check(bottomNavOff.id)
            } else {
                radioGroupBottomNav.check(bottomNavOn.id)
            }
            radioGroupBottomNav.jumpDrawablesToCurrentState()

            val darkTheme = settingsSharedViewModel.darkTheme.value
            switchDarkTheme.isChecked = darkTheme != null && darkTheme.isAble
            switchDarkTheme.jumpDrawablesToCurrentState()
        }
    }
}