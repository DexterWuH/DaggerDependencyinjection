package com.example.daggerdependencyinjection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.daggerdependencyinjection.ui.forecast.ForecastFragment
import com.example.daggerdependencyinjection.ui.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setInitialFragment()
        configureBottomNavBar()
    }

    private fun configureBottomNavBar() {
        val bottomNavBar: BottomNavigationView = findViewById(R.id.bottom_nav_bar)
        bottomNavBar.setOnItemSelectedListener { option ->
            when (option.itemId) {
                R.id.setting -> {
                    val fragment = SettingsFragment()
                    setFragment(fragment)
                    true
                }
                R.id.location -> {
                    setInitialFragment()
                    true
                }
                else -> true
            }
        }
    }

    private fun setInitialFragment() {
        val initialFragment = ForecastFragment()
        setFragment(initialFragment)
    }

    private fun setFragment(fragment: Fragment?) {
        fragment?.let {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, it)
                .addToBackStack(null)
                .commit()
        }
    }
}