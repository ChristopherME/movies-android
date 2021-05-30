package com.christopher_elias.experimental

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            /*
             * Interesting. If we not use android.R.id.content here our navigation library does not work as expected.
             */
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, FragmentA())
                .commitNow()
        }
    }
}