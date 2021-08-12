package com.maksimzotov.weatherhelper.presentation.ui.settings.indicators

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.maksimzotov.weatherhelper.databinding.IndicatorsSettingsFragmentBinding
import com.maksimzotov.weatherhelper.presentation.main.activity.SettingsSharedViewModel
import com.maksimzotov.weatherhelper.presentation.main.base.BaseFragment

class IndicatorsSettingsFragment
    : BaseFragment<IndicatorsSettingsFragmentBinding>(IndicatorsSettingsFragmentBinding::inflate) {

    private val viewModel by viewModels<IndicatorsSettingsViewModel>()
    private val settingsSharedViewModel by activityViewModels<SettingsSharedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            switchTemperature.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.showTemperature()
                } else {
                    viewModel.hideTemperature()
                }
            }

            switchHumidity.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.showHumidity()
                } else {
                    viewModel.hideHumidity()
                }
            }

            val temperature = settingsSharedViewModel.temperatureToShow.value
            switchTemperature.isChecked = temperature == null || temperature.isAble
            switchTemperature.jumpDrawablesToCurrentState()

            val humidity = settingsSharedViewModel.humidityToShow.value
            switchHumidity.isChecked = humidity == null || humidity.isAble
            switchHumidity.jumpDrawablesToCurrentState()
        }
    }
}