package com.maksimzotov.weatherhelper.presentation.ui.city

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.maksimzotov.weatherhelper.databinding.CityFragmentBinding
import com.maksimzotov.weatherhelper.presentation.main.ui.BaseFragment
import com.maksimzotov.weatherhelper.presentation.ui.city.viewpager.ForecastAdapter
import com.maksimzotov.weatherhelper.presentation.ui.indicators.IndicatorsFragment

class CityFragment : BaseFragment<CityFragmentBinding>(CityFragmentBinding::inflate) {

    private val viewModel by viewModels<CityViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = ForecastAdapter(
            listOf(
                IndicatorsFragment(),
                IndicatorsFragment()
            ),
            requireActivity().supportFragmentManager,
            lifecycle
        )

        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab, position ->
            tab.text = if (position == 0) {
                "Current"
            } else {
                "Forecast"
            }
        }.attach()
    }
}