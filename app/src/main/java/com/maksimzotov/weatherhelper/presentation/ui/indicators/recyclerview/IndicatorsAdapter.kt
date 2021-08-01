package com.maksimzotov.weatherhelper.presentation.ui.indicators.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maksimzotov.weatherhelper.R
import com.maksimzotov.weatherhelper.domain.entities.indicators.Indicator

class IndicatorsAdapter (
    val indicators: MutableList<Indicator>,
) : RecyclerView.Adapter<IndicatorsAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.indicator_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(R.layout.indicator_item, parent, false),
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = indicators[position].name
    }

    override fun getItemCount(): Int {
        return indicators.size
    }
}