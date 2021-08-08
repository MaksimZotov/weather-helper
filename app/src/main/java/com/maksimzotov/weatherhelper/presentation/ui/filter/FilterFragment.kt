package com.maksimzotov.weatherhelper.presentation.ui.filter

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.maksimzotov.weatherhelper.databinding.FilterFragmentBinding
import com.maksimzotov.weatherhelper.di.main.appComponent
import com.maksimzotov.weatherhelper.presentation.main.base.BaseFragment
import javax.inject.Inject

class FilterFragment : BaseFragment<FilterFragmentBinding>(FilterFragmentBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: FilterViewModel.Factory
    private val viewModel by viewModels<FilterViewModel> {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveFilter.setOnClickListener {
            //findNavController().popBackStack()
            viewModel.setCurrentFilter()
        }

        viewModel.currentFilter.observe(viewLifecycleOwner, { filter ->
            Toast.makeText(activity, filter.toString(), Toast.LENGTH_LONG).show()
        })
    }
}