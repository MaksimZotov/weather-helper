package com.maksimzotov.weatherhelper.presentation.ui.cities

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.maksimzotov.weatherhelper.R
import com.maksimzotov.weatherhelper.databinding.CitiesFragmentBinding
import com.maksimzotov.weatherhelper.presentation.main.ui.BaseFragment

class CitiesFragment : BaseFragment<CitiesFragmentBinding>(CitiesFragmentBinding::inflate) {

    private val viewModel by viewModels<CitiesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding.filterBottomSheet.addCity.setOnClickListener {
            findNavController().navigate(R.id.action_citiesFragment_to_selectionFragment)
        }

        return binding.root
    }
}
