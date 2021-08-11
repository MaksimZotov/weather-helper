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
import com.maksimzotov.weatherhelper.presentation.main.base.BaseFragment
import com.maksimzotov.weatherhelper.presentation.main.extensions.closeKeyboard
import com.maksimzotov.weatherhelper.presentation.main.util.NamesStorage
import com.maksimzotov.weatherhelper.presentation.ui.selection.recyclerview.NamesAdapter
import java.util.*
import javax.inject.Inject

class SelectionFragment :
    BaseFragment<SelectionFragmentBinding>(SelectionFragmentBinding::inflate),
    SearchView.OnQueryTextListener,
    NamesAdapter.OnCityClickListener {

    private val namesStorage = NamesStorage()
    private val namesAdapter = NamesAdapter(namesStorage.names, this)

    @Inject
    lateinit var viewModelFactory: SelectionViewModel.Factory
    private val viewModel by viewModels<SelectionViewModel> {
        viewModelFactory
    }

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

        val recyclerView = binding.citiesRecyclerView
        recyclerView.adapter = namesAdapter
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
        namesAdapter.setData(namesStorage.names.filter {
            it.lowercase(Locale.getDefault()).startsWith(filter)
        }.toMutableList())
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return onQueryTextSubmit(newText)
    }

    override fun onCityClick(position: Int) {
        viewModel.addCity(namesAdapter.names[position])
    }
}