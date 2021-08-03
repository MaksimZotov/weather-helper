package com.maksimzotov.weatherhelper.presentation.ui.settings

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import com.maksimzotov.weatherhelper.databinding.CitiesFragmentBinding
import com.maksimzotov.weatherhelper.databinding.SettingsFragmentBinding
import com.maksimzotov.weatherhelper.presentation.main.listeners.OnBottomNavVisibilityChangeListener
import com.maksimzotov.weatherhelper.presentation.main.base.BaseFragment
import com.maksimzotov.weatherhelper.presentation.main.base.TopLevelFragment
import com.maksimzotov.weatherhelper.presentation.main.listeners.NavDrawerLocker

class SettingsFragment
    : TopLevelFragment<SettingsFragmentBinding>(SettingsFragmentBinding::inflate) {

    private val viewModel by viewModels<SettingsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            switchDarkTheme.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
            radioGroupBottomNav.setOnCheckedChangeListener { group, checkedId ->
                val onBottomNavVisibilityChangeListener =
                    activity as OnBottomNavVisibilityChangeListener
                if (checkedId == bottomNavOn.id) {
                    onBottomNavVisibilityChangeListener.show()
                } else {
                    onBottomNavVisibilityChangeListener.hide()
                }
            }
        }
    }
}