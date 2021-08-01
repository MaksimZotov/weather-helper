package com.maksimzotov.weatherhelper.presentation.ui.settings

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import com.maksimzotov.weatherhelper.R
import com.maksimzotov.weatherhelper.databinding.SettingsFragmentBinding
import com.maksimzotov.weatherhelper.presentation.main.MainActivity
import com.maksimzotov.weatherhelper.presentation.main.ui.BaseFragment

class SettingsFragment : BaseFragment<SettingsFragmentBinding>(SettingsFragmentBinding::inflate) {

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
        }
    }
}