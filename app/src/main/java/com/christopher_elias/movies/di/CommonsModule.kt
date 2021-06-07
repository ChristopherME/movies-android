package com.christopher_elias.movies.di

import com.christopher_elias.common.models.mapper.MovieMapper
import com.christopher_elias.common.models.mapper.MovieMapperImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

/*
 * Created by Christopher Elias on 5/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

val commonModelsModule = module {
    // Singleton as its going to be used in more than one module.
    single<MovieMapper> { MovieMapperImpl(defaultDispatcher = get(named("defaultDispatcher"))) }
}
