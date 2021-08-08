package com.maksimzotov.weatherhelper.presentation.main.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.maksimzotov.weatherhelper.presentation.main.extensions.lockNavDrawer
import com.maksimzotov.weatherhelper.presentation.main.extensions.unlockNavDrawer

open class TopLevelFragment<VB: ViewBinding>(
    inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : BaseFragment<VB>(inflate) {

    override fun onResume() {
        super.onResume()
        requireActivity().unlockNavDrawer()
    }

    override fun onPause() {
        super.onPause()
        requireActivity().lockNavDrawer()
    }
}