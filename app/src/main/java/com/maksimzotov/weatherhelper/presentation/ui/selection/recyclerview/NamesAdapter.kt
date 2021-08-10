package com.maksimzotov.weatherhelper.presentation.ui.selection.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maksimzotov.weatherhelper.databinding.NameItemBinding

class NamesAdapter (
    var names: List<String>,
    private val onCityClickListener: OnCityClickListener
) : RecyclerView.Adapter<NamesAdapter.ViewHolder>() {

    interface OnCityClickListener {
        fun onCityClick(position: Int)
    }

    fun setData(names: List<String>) {
        this.names = names
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: NameItemBinding,
        private val onCityClickListener: OnCityClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onCityClickListener.onCityClick(adapterPosition)
            }
        }

        fun bind(name: String) {
            binding.cityName.text = name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NameItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onCityClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(names[position])
    }

    override fun getItemCount(): Int {
        return names.size
    }
}