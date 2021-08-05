package com.maksimzotov.weatherhelper.presentation.ui.city.settings

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.maksimzotov.weatherhelper.databinding.IndicatorsSettingsFragmentBinding
import com.maksimzotov.weatherhelper.presentation.main.activity.SettingsSharedViewModel
import com.maksimzotov.weatherhelper.presentation.main.base.BaseFragment

class IndicatorsSettingsFragment
    : BaseFragment<IndicatorsSettingsFragmentBinding>(IndicatorsSettingsFragmentBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel =
            ViewModelProvider(this).get(IndicatorsSettingsViewModel::class.java)

        binding.apply {
            switchTemperature.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    viewModel.showTemperature()
                } else {
                    viewModel.hideTemperature()
                }
            }
        }

        val settingsSharedViewModel =
            ViewModelProvider(requireActivity())
                .get(SettingsSharedViewModel::class.java)

        binding.apply {
            val temperature = settingsSharedViewModel.temperature.value
            switchTemperature.isChecked = temperature != null && temperature.isAble
            switchTemperature.jumpDrawablesToCurrentState()
        }
    }
}