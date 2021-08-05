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


        // Passing the activity to ViewModelProvider (i.e. using the shared view model
        // MainActivityViewModel) in order to show the settings state immediately.
        // If SettingsViewModel is used, a user will see animations of switchers and
        // radio buttons after opening the current fragment. In this case even
        // invoking jumpDrawablesToCurrentState() does not help.
        // The same thing in the Indicators fragment
        val activityViewModel =
            ViewModelProvider(requireActivity())
                .get(MainActivityViewModel::class.java)

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