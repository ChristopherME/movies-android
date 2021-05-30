package com.christopher_elias.movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.christopher_elias.movies.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.root_container, HomeContainerFragment())
                .commit()
        }
    }
}