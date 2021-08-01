package com.maksimzotov.weatherhelper.presentation.ui.cities

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
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
import com.maksimzotov.weatherhelper.presentation.main.ui.BaseFragment
import com.maksimzotov.weatherhelper.presentation.ui.cities.recyclerview.CitiesAdapter

class CitiesFragment :
    BaseFragment<CitiesFragmentBinding>(CitiesFragmentBinding::inflate),
    OnItemClickListener {

    private val viewModel by viewModels<CitiesViewModel>()
    private lateinit var citiesAdapter: CitiesAdapter
    private var prevBG: Drawable? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.filterBottomSheet.addCity.setOnClickListener {
            //findNavController().navigate(R.id.selectionFragment)
            citiesAdapter.cities.add(City("Some name", Temperature(-14, 35)))
            citiesAdapter.notifyDataSetChanged()
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

        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun isLongPressDragEnabled() = true
            override fun isItemViewSwipeEnabled() = true

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeMovementFlags(
                    ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                    ItemTouchHelper.START or ItemTouchHelper.END
                )
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                val habitFrom = citiesAdapter.cities.removeAt(from)
                citiesAdapter.cities.add(to, habitFrom)
                citiesAdapter.notifyItemMoved(from, to)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                citiesAdapter.cities.removeAt(viewHolder.adapterPosition)
                citiesAdapter.notifyItemRemoved(viewHolder.adapterPosition)
            }

            override fun onSelectedChanged(
                viewHolder: RecyclerView.ViewHolder?,
                actionState: Int
            ) {
                super.onSelectedChanged(viewHolder, actionState)
                if (
                    actionState == ItemTouchHelper.ACTION_STATE_DRAG ||
                    actionState == ItemTouchHelper.ACTION_STATE_SWIPE
                ) {
                    prevBG = viewHolder?.itemView?.background
                    viewHolder?.itemView?.background = ColorDrawable(Color.LTGRAY)
                }
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)
                viewHolder.itemView.background = prevBG
            }

        }).attachToRecyclerView(recyclerView)
    }

    override fun onItemClick(position: Int) {
        findNavController().navigate(R.id.cityFragment)
    }
}
