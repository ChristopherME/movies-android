package com.christopher_elias.movies.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

val coroutinesModule = module {
    factory(named("defaultDispatcher")) { providesDefaultDispatcher() }
    factory(named("ioDispatcher")) { providesIoDispatcher() }
    factory(named("mainDispatcher")) { providesMainDispatcher() }
}

internal fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

internal fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

internal fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main