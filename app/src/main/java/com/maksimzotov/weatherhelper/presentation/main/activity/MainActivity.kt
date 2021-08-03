package com.maksimzotov.weatherhelper.presentation.main.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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

    private val homeItem = R.id.item_home
    private val settingsItem = R.id.item_settings
    private val aboutItem = R.id.item_about
    private val citiesFragment = R.id.citiesFragment
    private val settingsFragment = R.id.settingsFragment
    private val aboutFragment = R.id.aboutFragment

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            val curFragment = navController.currentDestination?.id
                ?: return@setOnItemSelectedListener true
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
            } else {
                val nextFragment = when {
                    curItem == settingsItem && curFragment != settingsFragment -> settingsFragment
                    curItem == aboutItem && curFragment != aboutFragment -> aboutFragment
                    else -> return@setOnItemSelectedListener true
                }
                if (navController.backStack.any {it.destination.id == nextFragment}) {
                    navController.popBackStack()
                    navController.popBackStack()
                    navController.navigate(curFragment)
                }
                navController.navigate(nextFragment)
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

    override fun onBackPressed() {
        val navController = findNavController(R.id.nav_host_fragment)
        val prevDestination = navController.currentDestination?.id
        super.onBackPressed()
        val curDestination = navController.currentDestination?.id
        if (prevDestination == settingsFragment || prevDestination == aboutFragment) {
            binding.bottomNavigationView.menu.apply {
                val curItem = when (curDestination) {
                    aboutFragment -> getItem(2)
                    settingsFragment -> getItem(1)
                    else -> getItem(0)
                }
                curItem.isChecked = true
            }
        }
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