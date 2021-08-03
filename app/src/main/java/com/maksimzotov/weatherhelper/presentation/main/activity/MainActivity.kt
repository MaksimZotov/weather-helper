package com.maksimzotov.weatherhelper.presentation.main.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.maksimzotov.weatherhelper.R
import com.maksimzotov.weatherhelper.databinding.ActivityMainBinding
import com.maksimzotov.weatherhelper.presentation.main.listeners.NavDrawerLocker
import com.maksimzotov.weatherhelper.presentation.main.listeners.OnBottomNavVisibilityChangeListener

class MainActivity :
    AppCompatActivity(),
    OnBottomNavVisibilityChangeListener,
    NavDrawerLocker {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

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
            binding.navDrawer.closeDrawers()
            true
        }

        binding.navigationView.setNavigationItemSelectedListener { item ->
            binding.bottomNavigationView.selectedItemId = item.itemId
            true
        }

        setSupportActionBar(binding.toolbar)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.citiesFragment,
                R.id.settingsFragment,
                R.id.aboutFragment
            ),
            binding.navDrawer
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun show() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    override fun hide() {
        binding.bottomNavigationView.visibility = View.GONE
    }

    override fun lock() {
        binding.navDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun unlock() {
        binding.navDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }
}