package com.maksimzotov.weatherhelper.presentation.ui.cities.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maksimzotov.weatherhelper.databinding.CityItemBinding
import com.maksimzotov.weatherhelper.domain.entities.cities.City
import com.maksimzotov.weatherhelper.presentation.main.listeners.OnItemClickListener

class CitiesAdapter (
    val cities: MutableList<City>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: CityItemBinding,
        private val onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(adapterPosition)
            }
        }

        fun bind(city: City) {
            binding.cityName.setText(city.name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    override fun getItemCount(): Int {
        return cities.size
    }
}