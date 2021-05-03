package com.christopher_elias.movies

import android.app.Application
import com.christopher_elias.movies.di.connectivityModule
import com.christopher_elias.movies.di.coroutinesModule
import com.christopher_elias.movies.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.elias@loop-ideas.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Koin Android logger
            androidLogger()

            // Inject Android Context
            androidContext(this@MoviesApp)

            // Modules here
            modules(listOf(coroutinesModule, networkModule, connectivityModule))
        }
    }
}