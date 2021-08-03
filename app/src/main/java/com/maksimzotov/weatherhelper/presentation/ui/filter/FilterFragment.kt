package com.maksimzotov.weatherhelper.presentation.ui.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.maksimzotov.weatherhelper.R
import com.maksimzotov.weatherhelper.databinding.CityFragmentBinding
import com.maksimzotov.weatherhelper.databinding.FilterFragmentBinding
import com.maksimzotov.weatherhelper.presentation.main.base.BaseFragment

class FilterFragment : BaseFragment<FilterFragmentBinding>(FilterFragmentBinding::inflate) {

    private val viewModel by viewModels<FilterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveFilter.setOnClickListener {
            // ...
            findNavController().popBackStack()
        }
    }
}