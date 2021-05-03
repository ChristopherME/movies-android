package com.christopher_elias.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.christopher_elias.movies.presentation.movies_list.MoviesListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainerView, MoviesListFragment())
            .commit()
    }
}