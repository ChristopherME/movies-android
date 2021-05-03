package com.christopher_elias.movies.di

import com.christopher_elias.utils.connectivity.ConnectivityUtils
import com.christopher_elias.utils.connectivity.ConnectivityUtilsImpl
import org.koin.dsl.module

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.elias@loop-ideas.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

val connectivityModule = module {
    single<ConnectivityUtils> { ConnectivityUtilsImpl(get()) }
}