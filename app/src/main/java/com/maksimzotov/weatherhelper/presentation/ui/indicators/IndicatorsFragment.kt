package com.maksimzotov.weatherhelper.presentation.ui.indicators

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.maksimzotov.weatherhelper.databinding.IndicatorsFragmentBinding
import com.maksimzotov.weatherhelper.domain.entities.Temperature
import com.maksimzotov.weatherhelper.presentation.main.activity.SettingsSharedViewModel
import com.maksimzotov.weatherhelper.presentation.main.base.BaseFragment

class IndicatorsFragment(
    private val temperature: Temperature
) : BaseFragment<IndicatorsFragmentBinding>(IndicatorsFragmentBinding::inflate) {

    private val settingsSharedViewModel by activityViewModels<SettingsSharedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.indicatorMin.text = temperature.min.toString()
        binding.indicatorMax.text = temperature.max.toString()

        settingsSharedViewModel.temperature.observe(viewLifecycleOwner, { temperature ->
            if (temperature == null || temperature.isAble) {
                binding.indicatorTemperature.visibility = View.VISIBLE
            } else {
                binding.indicatorTemperature.visibility = View.GONE
            }
        })
    }
}