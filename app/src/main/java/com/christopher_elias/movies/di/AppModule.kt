package com.christopher_elias.movies.di

import android.content.Context
import com.christopher_elias.movies.R
import com.christopher_elias.movies.resource_provider.ResourceProviderImpl
import com.christopher_elias.movies.connectivity.ConnectivityUtilsImpl
import com.christopher_elias.utils.resource_provider.ResourceProvider
import com.christopher_elias.utils.connectivity.ConnectivityUtils
import org.koin.core.qualifier.named
import org.koin.dsl.module

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */


// Load here string resource provider
/**
 * TODO: Add your own API KEY in local.properties file
 */
val appModule = module {

    single(named("TMDB_KEY")) { provideApiKey(get()) }

    single<ResourceProvider> { ResourceProviderImpl(get()) }

    single<ConnectivityUtils> { ConnectivityUtilsImpl(get()) }
}

internal fun provideApiKey(
    context: Context
): String = context.getString(R.string.tmdb_key)
