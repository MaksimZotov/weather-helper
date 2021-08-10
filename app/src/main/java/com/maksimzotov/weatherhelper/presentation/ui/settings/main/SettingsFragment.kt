package com.maksimzotov.weatherhelper.presentation.ui.settings.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.maksimzotov.weatherhelper.databinding.SettingsFragmentBinding
import com.maksimzotov.weatherhelper.presentation.main.activity.SettingsSharedViewModel
import com.maksimzotov.weatherhelper.presentation.main.base.TopLevelFragment

class SettingsFragment
    : TopLevelFragment<SettingsFragmentBinding>(SettingsFragmentBinding::inflate) {

    private val viewModel by viewModels<SettingsViewModel>()
    private val settingsSharedViewModel by activityViewModels<SettingsSharedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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