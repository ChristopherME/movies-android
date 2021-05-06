package com.christopher_elias.movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.christopher_elias.movies.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val btmNavMain: BottomNavigationView by lazy { findViewById(R.id.bottomNavMain) }

    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.mainContainerView) as NavHostFragment)
            .navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        btmNavMain.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            handleBottomNavVisibility(fragmentId = destination.id)
        }
    }

    private fun handleBottomNavVisibility(fragmentId: Int) {
        btmNavMain.isVisible = (fragmentId == R.id.fragment_movies) ||
                (fragmentId == R.id.fragment_actors) ||
                (fragmentId == R.id.bottom_sheet_fragment_movie_detail)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}