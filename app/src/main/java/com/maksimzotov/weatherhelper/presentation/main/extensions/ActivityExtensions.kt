package com.maksimzotov.weatherhelper.presentation.main.extensions

import android.app.Activity
import android.view.inputmethod.InputMethodManager
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
