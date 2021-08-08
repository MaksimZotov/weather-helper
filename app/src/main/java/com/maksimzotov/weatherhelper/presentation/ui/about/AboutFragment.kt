package com.maksimzotov.weatherhelper.presentation.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maksimzotov.weatherhelper.R
import com.maksimzotov.weatherhelper.databinding.AboutFragmentBinding
import com.maksimzotov.weatherhelper.presentation.main.base.TopLevelFragment

class AboutFragment
    : TopLevelFragment<AboutFragmentBinding>(AboutFragmentBinding::inflate) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.about_fragment, container, false)
    }
}