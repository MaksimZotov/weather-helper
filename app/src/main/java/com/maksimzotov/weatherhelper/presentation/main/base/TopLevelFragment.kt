package com.maksimzotov.weatherhelper.presentation.main.base

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.viewbinding.ViewBinding
import com.maksimzotov.weatherhelper.presentation.main.listeners.NavDrawerLocker

abstract class TopLevelFragment<VB: ViewBinding>(
    inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : BaseFragment<VB>(inflate) {

    override fun onResume() {
        super.onResume()
        val activity = requireActivity()
        (activity
            .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        (activity as NavDrawerLocker).unlock()
    }

    override fun onPause() {
        super.onPause()
        (requireActivity() as NavDrawerLocker).lock()
    }
}