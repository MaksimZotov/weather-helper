package com.maksimzotov.weatherhelper.presentation.ui.cities.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maksimzotov.weatherhelper.databinding.CityItemBinding
import com.maksimzotov.weatherhelper.domain.entities.City

class CitiesAdapter (
    var cities: List<City>,
    private val onCityClickListener: OnCityClickListener
) : RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {

    interface OnCityClickListener {
        fun onCityClick(position: Int)
    }

    fun setData(cities: List<City>) {
        this.cities = cities
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: CityItemBinding,
        private val onCityClickListener: OnCityClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onCityClickListener.onCityClick(adapterPosition)
            }
        }

        fun bind(city: City) {
            binding.cityName.text = city.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onCityClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    override fun getItemCount(): Int {
        return cities.size
    }
}