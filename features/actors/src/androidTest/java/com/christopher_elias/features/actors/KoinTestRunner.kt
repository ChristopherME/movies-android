package com.christopher_elias.features.actors

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

/*
 * Created by Christopher Elias on 18/05/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

/*
 * Approach from https://proandroiddev.com/testing-your-isolated-fragments-with-koin-9d9d39f7b17e
 */
class KoinTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TestApplication::class.java.name, context)
    }
}