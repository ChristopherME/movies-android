package com.christopher_elias.movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.christopher_elias.movies.R
import com.christopher_elias.features.movies.presentation.ui.movies_list.MovieListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainerView, MovieListFragment())
            .commit()
    }
}