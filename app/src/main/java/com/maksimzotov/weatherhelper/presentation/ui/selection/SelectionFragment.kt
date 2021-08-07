package com.maksimzotov.weatherhelper.presentation.ui.selection

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.maksimzotov.weatherhelper.R
import com.maksimzotov.weatherhelper.databinding.SelectionFragmentBinding
import com.maksimzotov.weatherhelper.di.main.appComponent
import com.maksimzotov.weatherhelper.domain.entities.cities.City
import com.maksimzotov.weatherhelper.domain.entities.indicators.Temperature
import com.maksimzotov.weatherhelper.domain.usecases.loadcity.LoadCityUseCase
import com.maksimzotov.weatherhelper.presentation.main.base.BaseFragment
import com.maksimzotov.weatherhelper.presentation.ui.cities.recyclerview.CitiesAdapter
import java.util.*
import javax.inject.Inject

class SelectionFragment :
    BaseFragment<SelectionFragmentBinding>(SelectionFragmentBinding::inflate),
    SearchView.OnQueryTextListener,
    CitiesAdapter.OnCityClickListener {

    private lateinit var viewModel: SelectionViewModel
    private lateinit var citiesAdapter: CitiesAdapter
    private val citiesStub = setOf(
        City("Moscow", mapOf("Today" to Temperature(0, 5))),
        City("Kiev", mapOf("Today" to Temperature(5, 10))),
        City("Minsk", mapOf("Today" to Temperature(10, 15)))
    )

    @Inject
    lateinit var loadCityUseCase: LoadCityUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().appComponent.injectSelectionFragment(this)
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

        viewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return SelectionViewModel(loadCityUseCase) as T
                }
            }
        ).get(SelectionViewModel::class.java)

        viewModel.response.observe(viewLifecycleOwner, { response ->
            if (response.isSuccessful) {
                Toast.makeText(context, response.body().toString(), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context,response.toString(), Toast.LENGTH_SHORT).show()
            }
        })
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

    override fun onCityClick(name: String) {
        viewModel.getCity(name)
        //findNavController().popBackStack()
    }
}