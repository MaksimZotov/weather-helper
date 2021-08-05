package com.maksimzotov.weatherhelper.presentation.ui.city.indicators

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.maksimzotov.weatherhelper.databinding.IndicatorsFragmentBinding
import com.maksimzotov.weatherhelper.presentation.main.activity.SettingsSharedViewModel
import com.maksimzotov.weatherhelper.presentation.main.base.BaseFragment

class IndicatorsFragment
    : BaseFragment<IndicatorsFragmentBinding>(IndicatorsFragmentBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val settingsSharedViewModel =
            ViewModelProvider(requireActivity())
                .get(SettingsSharedViewModel::class.java)

        settingsSharedViewModel.temperature.observe(viewLifecycleOwner, { temperature ->
            if (temperature == null || temperature.isAble) {
                binding.indicatorTemperature.visibility = View.VISIBLE
            } else {
                binding.indicatorTemperature.visibility = View.GONE
            }
        })
    }
}