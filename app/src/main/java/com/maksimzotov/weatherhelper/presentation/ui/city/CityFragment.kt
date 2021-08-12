package com.maksimzotov.weatherhelper.presentation.ui.city

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.maksimzotov.weatherhelper.R
import com.maksimzotov.weatherhelper.databinding.CityFragmentBinding
import com.maksimzotov.weatherhelper.presentation.main.base.BaseFragment
import com.maksimzotov.weatherhelper.presentation.ui.city.viewpager.ForecastAdapter
import com.maksimzotov.weatherhelper.presentation.ui.indicators.IndicatorsFragment

class CityFragment : BaseFragment<CityFragmentBinding>(CityFragmentBinding::inflate) {

    private val args by navArgs<CityFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val city = args.city
        val fragments = city.temperatures.mapIndexed { index, temperature ->
            IndicatorsFragment(
                temperature,
                city.humidityList[index]
            )
        }
        val dates = city.dates.map { it.toString() }

        binding.viewPager.adapter = ForecastAdapter(
            fragments,
            childFragmentManager,
            lifecycle
        )

        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab, position ->
            tab.text = dates[position]
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.indicators_menu, menu)

        menu.findItem(R.id.menu_indicators).setOnMenuItemClickListener {
            findNavController().navigate(R.id.indicatorsSettingsFragment)
            true
        }
    }
}