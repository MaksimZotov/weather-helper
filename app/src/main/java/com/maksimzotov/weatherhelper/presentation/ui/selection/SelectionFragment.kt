package com.maksimzotov.weatherhelper.presentation.ui.selection

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.maksimzotov.weatherhelper.R
import com.maksimzotov.weatherhelper.databinding.SelectionFragmentBinding
import com.maksimzotov.weatherhelper.di.main.appComponent
import com.maksimzotov.weatherhelper.domain.entities.City
import com.maksimzotov.weatherhelper.domain.entities.Date
import com.maksimzotov.weatherhelper.domain.entities.Temperature
import com.maksimzotov.weatherhelper.presentation.main.base.BaseFragment
import com.maksimzotov.weatherhelper.presentation.main.extensions.closeKeyboard
import com.maksimzotov.weatherhelper.presentation.ui.cities.recyclerview.CitiesAdapter
import java.util.*
import javax.inject.Inject

class SelectionFragment :
    BaseFragment<SelectionFragmentBinding>(SelectionFragmentBinding::inflate),
    SearchView.OnQueryTextListener,
    CitiesAdapter.OnCityClickListener {

    @Inject
    lateinit var viewModelFactory: SelectionViewModel.Factory
    private val viewModel by viewModels<SelectionViewModel> {
        viewModelFactory
    }

    private lateinit var citiesAdapter: CitiesAdapter
    private val citiesStub = setOf(
        City("Moscow", listOf(), listOf(), listOf()),
        City("Kiev", listOf(), listOf(), listOf()),
        City("Minsk", listOf(), listOf(), listOf())
    )

    override fun onAttach(context: Context) {
        requireActivity().appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        citiesAdapter = CitiesAdapter(citiesStub.toMutableList(), this)
        val recyclerView = binding.citiesRecyclerView
        recyclerView.adapter = citiesAdapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL)
        )

        viewModel.apply {
            popBackstack.observe(viewLifecycleOwner, { flag ->
                if (flag) {
                    viewModel.popBackstack.value = false
                    findNavController().popBackStack()
                }
            })

            loadedCity.observe(viewLifecycleOwner, { response ->
                if (response.isSuccessful) {
                    Toast.makeText(context, response.body().toString(), Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context,response.toString(), Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    override fun onPause() {
        super.onPause()
        requireActivity().closeKeyboard()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val menuItem = menu.findItem(R.id.menu_search)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        val filter = query?.lowercase(Locale.getDefault()) ?: return true
        citiesAdapter.setData(citiesStub.filter {
            it.name.lowercase(Locale.getDefault()).startsWith(filter)
        }.toMutableList())
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return onQueryTextSubmit(newText)
    }

    override fun onCityClick(position: Int) {
        viewModel.addCity(citiesAdapter.cities[position].name)
    }
}