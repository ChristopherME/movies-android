package com.christopher_elias.experimental

import android.os.Bundle
import android.view.View
import com.christopher_elias.navigation.extensions.addFragmentExt
import com.christopher_elias.navigation.extensions.replaceFragmentExt
import com.google.android.material.button.MaterialButton

/*
 * Created by Christopher Elias on 29/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class FragmentA : LifecycleLoggerFragment(R.layout.fragment_a) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialButton>(R.id.btnAddFragment).setOnClickListener {
            addFragmentExt(
                newFragment = FragmentB(),
                addToBackStack = true
            )
        }

        view.findViewById<MaterialButton>(R.id.btnReplaceFragment).setOnClickListener {
            replaceFragmentExt(
                newFragment = FragmentB(),
                addToBackStack = true
            )
        }
    }
}