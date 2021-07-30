package com.maksimzotov.weatherhelper.presentation.ui.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.maksimzotov.weatherhelper.databinding.CitiesFragmentBinding
import com.maksimzotov.weatherhelper.presentation.main.ui.BaseFragment

class CitiesFragment : BaseFragment<CitiesFragmentBinding>(CitiesFragmentBinding::inflate) {

    private val viewModel by viewModels<CitiesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
        viewModel.checkDataBindingWithInclude.observe(viewLifecycleOwner, { boolean ->
            if (boolean) {
                Toast.makeText(context, "Everything works fine!", Toast.LENGTH_SHORT).show()
            }
        })
        return binding.root
    }
}