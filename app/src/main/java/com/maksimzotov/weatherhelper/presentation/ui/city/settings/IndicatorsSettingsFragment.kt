package com.maksimzotov.weatherhelper.presentation.ui.city.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maksimzotov.weatherhelper.R

class IndicatorsSettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.indicators_settings_fragment, container, false)
    }
}