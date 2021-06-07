package com.christopher_elias.movies.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.christopher_elias.features.actors.presentation.ui.actors_list.ActorsListFragment
import com.christopher_elias.features.movies.presentation.ui.movies_list.MovieListFragment
import com.christopher_elias.movies.R
import com.google.android.material.bottomnavigation.BottomNavigationView

/*
 * Created by Christopher Elias on 30/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

/*
 * This is like our "NavHostFragment" from the jetpack library. What? You didn't knew that was a real fragment? 😂
 *
 * Approach taken from [https://medium.com/@oluwabukunmi.aluko/bottom-navigation-view-with-fragments-a074bfd08711]
 * The approach has it's downsides too (they're listed in the article), but for now we are just going to set up
 * the screenOrientation to portrait only in order to avoid some of those bugs.
 *
 * For now, It's out of the scope of the project provide a deeper implementation of BottomNav...
 *
 * TODO: handle & improve the approach taken from @oluwabukunmi inside the :core:navigation library.
 *   Checkout the README file inside the :core:navigation.
 */
class HomeContainerFragment : Fragment(R.layout.fragment_home) {

    private val moviesFragment: MovieListFragment by lazy { MovieListFragment() }
    private val actorsFragment: ActorsListFragment by lazy { ActorsListFragment() }
    private var activeFragment: Fragment? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            addFragments()
            setUpBottomNavItemSelectedListener()
        }
    }

    private fun addFragments() {
        if (childFragmentManager.fragments.isEmpty()) {
            // Set Up the active fragment
            activeFragment = moviesFragment

            // Add fragments to the FragmentContainer
            childFragmentManager.beginTransaction()
                .add(R.id.homeContainerView, actorsFragment, "ActorsListFragment")
                .hide(actorsFragment)
                .add(R.id.homeContainerView, moviesFragment, "MovieListFragment")
                .commit()
        }
    }

    private fun setUpBottomNavItemSelectedListener() {
        view?.findViewById<BottomNavigationView>(R.id.bottomNavHome)
            ?.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_feature_movies -> {
                        childFragmentManager.beginTransaction()
                            .hide(activeFragment!!)
                            .show(moviesFragment)
                            .commit()
                        activeFragment = moviesFragment
                        return@setOnNavigationItemSelectedListener true
                    }
                    R.id.nav_feature_actors -> {
                        childFragmentManager.beginTransaction()
                            .hide(activeFragment!!)
                            .show(actorsFragment)
                            .commit()
                        activeFragment = actorsFragment
                        return@setOnNavigationItemSelectedListener true
                    }
                }
                return@setOnNavigationItemSelectedListener false
            }
    }

}