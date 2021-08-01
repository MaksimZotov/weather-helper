package com.maksimzotov.weatherhelper.presentation.ui.indicators

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.maksimzotov.weatherhelper.databinding.IndicatorsFragmentBinding
import com.maksimzotov.weatherhelper.domain.entities.indicators.Temperature
import com.maksimzotov.weatherhelper.presentation.main.ui.BaseFragment
import com.maksimzotov.weatherhelper.presentation.ui.cities.CitiesViewModel
import com.maksimzotov.weatherhelper.presentation.ui.cities.recyclerview.CitiesAdapter
import com.maksimzotov.weatherhelper.presentation.ui.indicators.recyclerview.IndicatorsAdapter

class IndicatorsFragment : BaseFragment<IndicatorsFragmentBinding>(IndicatorsFragmentBinding::inflate) {

    private val viewModel by viewModels<CitiesViewModel>()
    private lateinit var indicatorsAdapter: IndicatorsAdapter
    private var prevBG: Drawable? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.indicatorsRecyclerView

        indicatorsAdapter = IndicatorsAdapter(mutableListOf(Temperature(-14, 35)))

        recyclerView.adapter = indicatorsAdapter

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
                val habitFrom = indicatorsAdapter.indicators.removeAt(from)
                indicatorsAdapter.indicators.add(to, habitFrom)
                indicatorsAdapter.notifyItemMoved(from, to)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                indicatorsAdapter.indicators.removeAt(viewHolder.adapterPosition)
                indicatorsAdapter.notifyItemRemoved(viewHolder.adapterPosition)
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
}