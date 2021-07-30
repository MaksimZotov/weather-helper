package com.maksimzotov.weatherhelper.presentation.ui.selection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.maksimzotov.weatherhelper.R

class SelectionFragment : Fragment() {

    private val viewModel by viewModels<SelectionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.selection_fragment, container, false)
    }
}