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
import com.maksimzotov.weatherhelper.presentation.main.extensions.checkInternet
import com.maksimzotov.weatherhelper.presentation.main.extensions.closeKeyboard
import com.maksimzotov.weatherhelper.presentation.main.util.NamesStorage
import com.maksimzotov.weatherhelper.presentation.ui.selection.recyclerview.NamesAdapter
import java.util.*
import javax.inject.Inject

class SelectionFragment :
    BaseFragment<SelectionFragmentBinding>(SelectionFragmentBinding::inflate),
    SearchView.OnQueryTextListener,
    NamesAdapter.OnCityClickListener {

    @Inject
    lateinit var viewModelFactory: SelectionViewModel.Factory
    private val viewModel by viewModels<SelectionViewModel> {
        viewModelFactory
    }

    private val namesStorage = NamesStorage()
    private val namesAdapter = NamesAdapter(namesStorage.names, this)

    private lateinit var failedToLoadCityString: String
    private lateinit var loadingTheString: String
    private lateinit var noInternetString: String

    override fun onAttach(context: Context) {
        requireActivity().appComponent.inject(this)
        super.onAttach(context)

        failedToLoadCityString = getString(R.string.failed_to_load_the_city)
        loadingTheString = getString(R.string.loading_the)
        noInternetString = getString(R.string.you_have_not_internet)
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

            error.observe(viewLifecycleOwner, { error ->
                if (error) {
                    viewModel.error.value = false
                    notifyAboutFail()
                }
            })
            loadedCity.observe(viewLifecycleOwner, { response ->
                if (!response.isSuccessful) {
                    notifyAboutFail()
                }
            })
        }
    }
    private fun notifyAboutFail() {
        Toast.makeText(context, failedToLoadCityString, Toast.LENGTH_SHORT).show()
        binding.citiesRecyclerView.visibility = View.VISIBLE
        binding.messageLoading.visibility = View.GONE
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
        if (requireActivity().checkInternet()) {
            val name = namesAdapter.names[position]
            requireActivity().closeKeyboard()
            notifyAboutLoading(name)
            viewModel.addCity(name)
        } else {
            Toast.makeText(context, noInternetString, Toast.LENGTH_SHORT).show()
        }
    }

    private fun notifyAboutLoading(name: String) {
        binding.apply {
            citiesRecyclerView.visibility = View.GONE
            messageLoading.visibility = View.VISIBLE
            messageLoading.text = "$loadingTheString $name..."
        }
    }
}