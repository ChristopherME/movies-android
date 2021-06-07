package com.christopher_elias.experimental

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/*
 * Created by Christopher Elias on 29/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */


/**
 * a [Fragment] class that logs every lifecycle stage.
 */
abstract class LifecycleLoggerFragment(
    private val layoutId: Int
) : Fragment(layoutId) {

    // 1
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(this::class.java.simpleName, "~ LIFECYCLE: 1 onCreate ${this}~")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(this::class.java.simpleName, "~ LIFECYCLE: 2 onCreateView ~")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    // 3
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(this::class.java.simpleName, "~ LIFECYCLE: 3 onViewCreated ~")
        super.onViewCreated(view, savedInstanceState)
    }

    // 4
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        Log.d(this::class.java.simpleName, "~ LIFECYCLE: 4 onViewStateRestored ~")
        super.onViewStateRestored(savedInstanceState)
    }

    // 5
    override fun onStart() {
        Log.d(this::class.java.simpleName, "~ LIFECYCLE: 5 onStart ~")
        super.onStart()
    }

    // 6
    override fun onResume() {
        Log.d(this::class.java.simpleName, "~ LIFECYCLE: 6 onResume ~")
        super.onResume()
    }

    // 7
    override fun onPause() {
        Log.d(this::class.java.simpleName, "~ LIFECYCLE: 7 onPause ~")
        super.onPause()
    }

    // 8
    override fun onStop() {
        Log.d(this::class.java.simpleName, "~ LIFECYCLE: 8 onStop ~")
        super.onStop()
    }

    // 9
    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(this::class.java.simpleName, "~ LIFECYCLE: 9 onSaveInstanceState ~")
        super.onSaveInstanceState(outState)
    }

    // 10
    override fun onDestroyView() {
        Log.d(this::class.java.simpleName, "~ LIFECYCLE: 10 onDestroyView ~")
        super.onDestroyView()
    }

    // 11
    override fun onDestroy() {
        Log.d(this::class.java.simpleName, "~ LIFECYCLE: 11 onDestroy ~")
        super.onDestroy()
    }
}