package com.maksimzotov.weatherhelper.presentation.ui.cities

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.maksimzotov.weatherhelper.R
import com.maksimzotov.weatherhelper.databinding.CitiesFragmentBinding
import com.maksimzotov.weatherhelper.domain.entities.cities.City
import com.maksimzotov.weatherhelper.domain.entities.indicators.Temperature
import com.maksimzotov.weatherhelper.presentation.main.listeners.OnItemClickListener
import com.maksimzotov.weatherhelper.presentation.main.base.BaseFragment
import com.maksimzotov.weatherhelper.presentation.main.base.TopLevelFragment
import com.maksimzotov.weatherhelper.presentation.main.listeners.NavDrawerLocker
import com.maksimzotov.weatherhelper.presentation.ui.cities.recyclerview.CitiesAdapter


class CitiesFragment :
    TopLevelFragment<CitiesFragmentBinding>(CitiesFragmentBinding::inflate),
    SearchView.OnQueryTextListener,
    OnItemClickListener {

    private val viewModel by viewModels<CitiesViewModel>()
    private lateinit var citiesAdapter: CitiesAdapter
    private var prevBG: Drawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addCity.setOnClickListener {
            findNavController().navigate(R.id.selectionFragment)
        }

        binding.filterBottomSheet.editFilter.setOnClickListener {
            findNavController().navigate(R.id.filterFragment)
        }
        
        val recyclerView = binding.indicatorsRecyclerView
        citiesAdapter = CitiesAdapter(
            mutableListOf(City("Some name", Temperature(-14, 35))),
            this
        )
        recyclerView.adapter = citiesAdapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL)
        )
    }

    override fun onItemClick(position: Int) {
        findNavController().navigate(R.id.cityFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val menuItem = menu.findItem(R.id.menu_search)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Toast.makeText(requireContext(), "Submit", Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Toast.makeText(requireContext(), "Change", Toast.LENGTH_SHORT).show()
        return true
    }
}
