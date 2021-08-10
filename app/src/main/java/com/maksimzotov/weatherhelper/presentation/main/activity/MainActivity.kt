package com.maksimzotov.weatherhelper.presentation.main.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.maksimzotov.weatherhelper.R
import com.maksimzotov.weatherhelper.databinding.ActivityMainBinding
import com.maksimzotov.weatherhelper.presentation.main.listeners.NavDrawerLocker

class MainActivity :
    AppCompatActivity(),
    NavDrawerLocker {

    private val settingsSharedViewModel by viewModels<SettingsSharedViewModel>()

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    private val homeItem = R.id.item_home
    private val settingsItem = R.id.item_settings
    private val aboutItem = R.id.item_about
    private val citiesFragment = R.id.citiesFragment
    private val settingsFragment = R.id.settingsFragment
    private val aboutFragment = R.id.aboutFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment)

        configureBottomNavigationView()
        configureNavigationView()
        configureDarkTheme()
        configureToolbar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
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

    override fun lockNavDrawer() {
        binding.navDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun unlockNavDrawer() {
        binding.navDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    @SuppressLint("RestrictedApi")
    private fun configureBottomNavigationView() {
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

        settingsSharedViewModel.bottomNavigation.observe(this, { bottomNavigation ->
            bottomNavigation ?: return@observe
            if (bottomNavigation.isAble) {
                binding.bottomNavigationView.visibility = View.VISIBLE
            } else {
                binding.bottomNavigationView.visibility = View.GONE
            }
        })
    }

    private fun configureNavigationView() {
        binding.navigationView.setNavigationItemSelectedListener { item ->
            binding.bottomNavigationView.selectedItemId = item.itemId
            true
        }
    }

    private fun configureDarkTheme() {
        settingsSharedViewModel.darkTheme.observe(this, { darkTheme ->
            darkTheme ?: return@observe
            if (darkTheme.isAble) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        })
    }

    private fun configureToolbar() {
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
}