package com.maksimzotov.weatherhelper.presentation.ui.indicators

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.maksimzotov.weatherhelper.databinding.IndicatorsFragmentBinding
import com.maksimzotov.weatherhelper.domain.entities.Humidity
import com.maksimzotov.weatherhelper.domain.entities.Temperature
import com.maksimzotov.weatherhelper.presentation.main.activity.SettingsSharedViewModel
import com.maksimzotov.weatherhelper.presentation.main.base.BaseFragment
import com.maksimzotov.weatherhelper.presentation.main.util.ToStringConverter

class IndicatorsFragment(
    private val temperature: Temperature,
    private val humidity: Humidity,
) : BaseFragment<IndicatorsFragmentBinding>(IndicatorsFragmentBinding::inflate) {

    private val settingsSharedViewModel by activityViewModels<SettingsSharedViewModel>()

    private val toStrConverter = ToStringConverter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            indicatorTemperatureMin.text = toStrConverter.convertTemperature(temperature.min)
            indicatorTemperatureMax.text = toStrConverter.convertTemperature(temperature.max)

            indicatorHumidityMin.text = toStrConverter.convertHumidity(humidity.min)
            indicatorHumidityMax.text = toStrConverter.convertHumidity(humidity.max)

            settingsSharedViewModel.temperature.observe(viewLifecycleOwner, { temperature ->
                setVisibility(indicatorTemperature, temperature == null || temperature.isAble)
            })

            settingsSharedViewModel.humidity.observe(viewLifecycleOwner, { humidity ->
                setVisibility(indicatorHumidity, humidity == null || humidity.isAble)
            })
        }
    }

    private fun setVisibility(view: View, visible: Boolean) {
        if (visible) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }
}