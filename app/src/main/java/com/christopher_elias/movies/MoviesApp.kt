package com.christopher_elias.movies

import android.app.Application
import com.christopher_elias.features.actors.di.featureActorsModule
import com.christopher_elias.features.movies.di.featureMoviesModule
import com.christopher_elias.movies.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Inject Android Context
            androidContext(this@MoviesApp)

            // Modules here
            modules(
                listOf(
                    appModule,
                    coroutinesModule,
                    networkModule,
                    middleWareModule,
                    commonModelsModule,
                    featureMoviesModule,
                    featureActorsModule
                )
            )
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}