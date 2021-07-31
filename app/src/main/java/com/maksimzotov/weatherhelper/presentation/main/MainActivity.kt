package com.maksimzotov.weatherhelper.presentation.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.maksimzotov.weatherhelper.R
import com.maksimzotov.weatherhelper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)

        val homeItem = R.id.item_home
        val settingsItem = R.id.item_settings
        val aboutItem = R.id.item_about
        val citiesFragment = R.id.citiesFragment
        val settingsFragment = R.id.settingsFragment
        val aboutFragment = R.id.aboutFragment

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            val curFragment = navController.currentDestination?.id
            val curItem = item.itemId
            if (curItem == homeItem) {
                if (curFragment !in listOf(settingsFragment, aboutFragment)) {
                    navController.popBackStack(citiesFragment, false)
                } else {
                    val lastFragmentInHome =
                        navController
                            .backStack
                            .last {
                                it.destination.id !in listOf(settingsFragment, aboutFragment)
                            }
                    navController.popBackStack(lastFragmentInHome.destination.id, false)
                }
            } else if (curItem == settingsItem && curFragment != settingsFragment) {
                navController.navigate(R.id.settingsFragment)
            } else if (curItem == aboutItem && curFragment != aboutFragment) {
                navController.navigate(R.id.aboutFragment)
            }
            return@setOnItemSelectedListener true
        }
    }
}