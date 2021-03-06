package com.maksimzotov.weatherhelper.presentation.ui.cities

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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.maksimzotov.weatherhelper.R
import com.maksimzotov.weatherhelper.databinding.CitiesFragmentBinding
import com.maksimzotov.weatherhelper.di.main.appComponent
import com.maksimzotov.weatherhelper.domain.entities.City
import com.maksimzotov.weatherhelper.domain.entities.Filter
import com.maksimzotov.weatherhelper.presentation.entities.filters.Preferences
import com.maksimzotov.weatherhelper.presentation.main.base.TopLevelFragment
import com.maksimzotov.weatherhelper.presentation.main.extensions.checkInternet
import com.maksimzotov.weatherhelper.presentation.main.extensions.closeKeyboard
import com.maksimzotov.weatherhelper.presentation.main.extensions.isNightModeOn
import com.maksimzotov.weatherhelper.presentation.ui.cities.recyclerview.CitiesAdapter
import com.maksimzotov.weatherhelper.presentation.main.util.Colors
import java.util.*
import javax.inject.Inject

class CitiesFragment :
    TopLevelFragment<CitiesFragmentBinding>(CitiesFragmentBinding::inflate),
    SearchView.OnQueryTextListener,
    CitiesAdapter.OnCityClickListener {

    @Inject
    lateinit var viewModelFactory: CitiesViewModel.Factory
    private val viewModel by viewModels<CitiesViewModel> {
        viewModelFactory
    }

    private val colors = Colors()

    private lateinit var citiesAdapter: CitiesAdapter

    private lateinit var loadingString: String
    private lateinit var noInternetString: String
    private lateinit var updatingString: String
    private lateinit var updatedString: String

    override fun onAttach(context: Context) {
        requireActivity().appComponent.inject(this)
        super.onAttach(context)

        loadingString = getString(R.string.loading)
        noInternetString = getString(R.string.you_have_not_internet)
        updatingString = "${getString(R.string.updating)}..."
        updatedString = getString(R.string.updated)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureBinding()
        configureViewModel()
        configureRecyclerView()
    }

    override fun onPause() {
        super.onPause()
        requireActivity().closeKeyboard()
    }

    override fun onCityClick(position: Int) {
        val city = citiesAdapter.cities[position]
        val action = CitiesFragmentDirections.actionCitiesFragmentToCityFragment(city)
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.searh_and_update_menu, menu)

        val menuItem = menu.findItem(R.id.menu_search)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        menu.findItem(R.id.menu_update).setOnMenuItemClickListener {
            if (requireActivity().checkInternet()) {
                Toast.makeText(context, updatingString, Toast.LENGTH_LONG).show()
                viewModel.updateCities(citiesAdapter.cities)
            } else {
                Toast.makeText(context, noInternetString, Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        val filter = query?.lowercase(Locale.getDefault()) ?: return true
        val cities = viewModel.cities.value ?: return true
        citiesAdapter.setData(cities.filter { city ->
            city.name.lowercase(Locale.getDefault()).startsWith(filter)
        }.toMutableList())
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return onQueryTextSubmit(newText)
    }

    private fun configureBinding() {
        binding.apply {
            addCity.setOnClickListener {
                findNavController().navigate(R.id.selectionFragment)
            }
            filterBottomSheet.editFilter.setOnClickListener {
                val action =
                    CitiesFragmentDirections.actionCitiesFragmentToFilterFragment(
                        when (filterBottomSheet.filterPreference.selectedItemPosition) {
                            0 -> Preferences.CURRENT_FILTER
                            1 -> Preferences.TEMPERATE_CLIMATE
                            else -> Preferences.DRY_CLIMATE
                        }
                    )
                findNavController().navigate(action)
            }
        }
    }

    private fun configureViewModel() {
        viewModel.apply {
            cities.observe(viewLifecycleOwner, { cities ->
                if (cities != null && citiesAreUpdated) {
                    if (userMustBeNotifiedAboutUpdatedCities) {
                        Toast.makeText(context, updatedString, Toast.LENGTH_SHORT).show()
                    }
                    val filter = filter.value
                    if (filter != null) {
                        if (cities.isEmpty()) {
                            citiesAdapter.setData(cities)
                            return@observe
                        }
                        checkMatchingToFilter(cities, filter)
                    } else {
                        citiesAdapter.setData(cities)
                    }
                }
            })
            filter.observe(viewLifecycleOwner, { filter ->
                if (filter != null) {
                    val cities = cities.value ?: return@observe
                    checkMatchingToFilter(cities, filter)
                }
            })
        }
    }
    private fun checkMatchingToFilter(cities: List<City>, filter: Filter) {
        cities.forEach { it.checkMatchingToFilter(filter) }
        citiesAdapter.setData(cities)
    }

    private fun configureRecyclerView() {
        citiesAdapter = CitiesAdapter(
            listOf(),
            this,
            requireActivity().isNightModeOn(),
            colors
        )

        val recyclerView = binding.indicatorsRecyclerView
        recyclerView.adapter = citiesAdapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL)
        )

        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun isItemViewSwipeEnabled() = true

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeMovementFlags(
                    0,
                    ItemTouchHelper.START or ItemTouchHelper.END
                )
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.removeCity(viewHolder.adapterPosition)
            }

            override fun onSelectedChanged(
                viewHolder: RecyclerView.ViewHolder?,
                actionState: Int
            ) {
                super.onSelectedChanged(viewHolder, actionState)
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    viewHolder?.itemView?.background =
                        colors.getColorOnPressed(requireActivity().isNightModeOn())
                }
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)
                if (viewHolder.adapterPosition >= 0) {
                    viewHolder.itemView.background = colors.getStandardColor(
                        requireActivity().isNightModeOn(),
                        citiesAdapter.cities[viewHolder.adapterPosition].isMatchesToFilter
                    )
                }
            }

        }).attachToRecyclerView(recyclerView)
    }
}