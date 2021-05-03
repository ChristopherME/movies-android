package com.christopher_elias.movies.di

import android.content.Context
import com.christopher_elias.movies.R
import org.koin.core.qualifier.named
import org.koin.dsl.module

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.elias@loop-ideas.com
 *
 * Loop Ideas
 * Lima, Peru.
 */


// Load here string resource provider
/**
 * TODO: Add your own API KEY in local.properties file
 */
val appModule = module {
    single(named("TMDB_KEY")) { provideApiKey(get()) }
}

internal fun provideApiKey(
    context: Context
): String = context.getString(R.string.tmdb_key)
