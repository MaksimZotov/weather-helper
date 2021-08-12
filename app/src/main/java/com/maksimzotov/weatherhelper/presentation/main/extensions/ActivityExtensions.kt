package com.maksimzotov.weatherhelper.presentation.main.extensions

import android.app.Activity
import android.content.res.Configuration
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.maksimzotov.weatherhelper.presentation.main.listeners.NavDrawerLocker

fun Activity.closeKeyboard() {
    (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
    .hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}

fun Activity.lockNavDrawer() {
    if (this is NavDrawerLocker) {
        lockNavDrawer()
    } else {
        throw IllegalStateException("Your activity does not implement NavDrawerLocker")
    }
}

fun Activity.unlockNavDrawer() {
    if (this is NavDrawerLocker) {
        unlockNavDrawer()
    } else {
        throw IllegalStateException("Your activity does not implement NavDrawerLocker")
    }
}

fun Activity.isNightModeOn(): Boolean {
    val mode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return mode == Configuration.UI_MODE_NIGHT_YES
}

fun Activity.setTitleInAppBar(title: CharSequence) {
    if (this is AppCompatActivity) {
        supportActionBar?.title = title
    }
}

