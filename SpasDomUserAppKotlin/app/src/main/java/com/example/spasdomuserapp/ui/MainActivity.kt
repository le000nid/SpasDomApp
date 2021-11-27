package com.example.spasdomuserapp.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.ActivityMainBinding
import com.example.spasdomuserapp.util.IS_LOGGED
import com.example.spasdomuserapp.util.PREF_AUTH

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var preference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginSetUp()
        navigationConfigurationSetUp()
    }

    private fun loginSetUp() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)

        preference = getSharedPreferences(PREF_AUTH, Context.MODE_PRIVATE)
        val isLogged = preference.getBoolean(IS_LOGGED, false)

        if (!isLogged) {
            graph.startDestination = R.id.loginFragment
        } else {
            graph.startDestination = R.id.homeFragment
        }
        navHostFragment.navController.graph = graph
    }

    private fun navigationConfigurationSetUp() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.servicesFragment,
                R.id.chatFragment,
                R.id.paymentFragment,
                R.id.profileFragment
            ))

        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> showBottomNav()
                R.id.servicesFragment -> showBottomNav()
                R.id.chatFragment -> showBottomNav()
                R.id.paymentFragment -> showBottomNav()
                R.id.profileFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun showBottomNav() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.bottomNavigationView.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}