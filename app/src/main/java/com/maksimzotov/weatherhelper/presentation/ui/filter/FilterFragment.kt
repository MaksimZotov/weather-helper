package com.maksimzotov.weatherhelper.presentation.ui.filter

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.slider.RangeSlider
import com.maksimzotov.weatherhelper.databinding.FilterFragmentBinding
import com.maksimzotov.weatherhelper.di.main.appComponent
import com.maksimzotov.weatherhelper.presentation.main.base.BaseFragment
import java.util.*
import javax.inject.Inject

class FilterFragment : BaseFragment<FilterFragmentBinding>(FilterFragmentBinding::inflate) {

    @Inject
    lateinit var factory: FilterViewModel.Factory.FactoryForVMFactory
    private val args by navArgs<FilterFragmentArgs>()
    private val viewModel by viewModels<FilterViewModel> {
        factory.create(args.preference)
    }

    private val dateConverter: DateConverter = DateConverter()
    private val dateFormat = dateConverter.dateFormat

    private val onFirstDateSetListener =
        DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            viewModel.firstDate.value = dateConverter.fromIntsToString(dayOfMonth, month, year)
        }

    private val onLastDateSetListener =
        DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            viewModel.lastDate.value = dateConverter.fromIntsToString(dayOfMonth, month, year)
        }


    override fun onAttach(context: Context) {
        requireActivity().appComponent.inject(this)
        super.onAttach(context)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val date = dateConverter.fromStringToList(dateFormat.format(Calendar.getInstance().time))
        val day = date[0]
        val month = date[1]
        val year = date[2]

        binding.apply {
            firstDay.setOnClickListener {
                DatePickerDialog(requireActivity(), onFirstDateSetListener, year, month, day).show()
            }
            lastDay.setOnClickListener {
                DatePickerDialog(requireActivity(), onLastDateSetListener, year, month, day).show()
            }

            rangeSliderTemperature
                .addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {

                override fun onStartTrackingTouch(slider: RangeSlider) {
                    rangeSliderTextTemperature.text = "Temperature [?; ?]"
                }
                override fun onStopTrackingTouch(slider: RangeSlider) {
                    viewModel.rangeTemperature.value =
                        slider.values[0].toInt() to slider.values[1].toInt()
                }
            })
            rangeSliderTemperature.setLabelFormatter { value ->
                "+${value.toInt()}"
            }

            rangeSliderHumidity
                .addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {

                    override fun onStartTrackingTouch(slider: RangeSlider) {
                        rangeSliderTextHumidity.text = "Humidity [?; ?]"
                    }
                    override fun onStopTrackingTouch(slider: RangeSlider) {
                        viewModel.rangeHumidity.value =
                            slider.values[0].toInt() to slider.values[1].toInt()
                    }
                })
            rangeSliderHumidity.setLabelFormatter { value ->
                "${value.toInt()}%"
            }

            saveFilter.setOnClickListener {
                viewModel.setCurrentFilter()
            }
        }

        viewModel.apply {
            popBackstack.observe(viewLifecycleOwner, { flag ->
                if (flag) {
                    viewModel.popBackstack.value = false
                    findNavController().popBackStack()
                }
            })
            firstDate.observe(viewLifecycleOwner, { day -> binding.firstDay.text = day })
            lastDate.observe(viewLifecycleOwner, { day -> binding.lastDay.text = day })
            rangeTemperature.observe(viewLifecycleOwner, { range ->
                binding.rangeSliderTextTemperature.text =
                    "Temperature [+${range.first}; +${range.second}]"

                binding.rangeSliderTemperature.setValues(
                    range.first.toFloat(),
                    range.second.toFloat()
                )
            })
            rangeHumidity.observe(viewLifecycleOwner, { range ->
                binding.rangeSliderTextHumidity.text =
                    "Humidity [${range.first}%; ${range.second}%]"

                binding.rangeSliderHumidity.setValues(
                    range.first.toFloat(),
                    range.second.toFloat()
                )
            })

            filter.observe(viewLifecycleOwner, { filter ->
                if (filter == null || !flagSetCurrentFilter) {
                    return@observe
                }
                val minTemperature = filter.temperature.min
                val maxTemperature = filter.temperature.max
                val minHumidity = filter.humidity.min
                val maxHumidity = filter.humidity.max
                firstDate.value = filter.startDate.toString()
                lastDate.value = filter.endDate.toString()
                rangeTemperature.value = minTemperature to maxTemperature
                rangeHumidity.value = minHumidity to maxHumidity
                binding.apply {
                    listOf(
                        firstDay,
                        lastDay,
                        rangeSliderTextTemperature,
                        rangeSliderTemperature
                    ).forEach { it.visibility = View.VISIBLE }
                }
            })
        }
    }
}