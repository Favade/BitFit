package com.example.bitfit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val food = mutableListOf<FoodDetailsEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager


        val logFragment: Fragment = LogFragment()
        val dashboardFragment: Fragment = DashboardFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_log -> fragment = logFragment
                R.id.nav_dashboard -> fragment = dashboardFragment
            }
            fragmentManager.beginTransaction().replace(R.id.rlContainer, fragment).commit()
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.nav_log
    }

    private fun replaceFragment(foodListFragment: LogFragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, foodListFragment)
        fragmentTransaction.commit()

    }
}