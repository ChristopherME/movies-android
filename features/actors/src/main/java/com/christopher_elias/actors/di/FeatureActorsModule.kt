package com.christopher_elias.actors.di

import com.christopher_elias.actors.data.data_source.ActorsRemoteDataSource
import com.christopher_elias.actors.data.repository.ActorsRepositoryImpl
import com.christopher_elias.actors.data_source.remote.ActorsRemoteDataSourceImpl
import com.christopher_elias.actors.data_source.remote.retrofit.ActorsService
import com.christopher_elias.actors.domain.repository.ActorsRepository
import com.christopher_elias.actors.mapper.ActorsMapper
import com.christopher_elias.actors.mapper.ActorsMapperImpl
import com.christopher_elias.actors.presentation.list.ActorsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.elias@loop-ideas.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

val featureActorsModule = module {

    factory { provideActorsService(retrofit = get()) }

    factory<ActorsRemoteDataSource> {
        ActorsRemoteDataSourceImpl(
            connectivityUtils = get(),
            ioDispatcher = get(named("ioDispatcher")),
            errorAdapter = get(),
            actorsService = get(),
            tmdbKey = get(named("TMDB_KEY"))
        )
    }

    factory<ActorsMapper> { ActorsMapperImpl(defaultDispatcher = get(named("defaultDispatcher"))) }

    factory<ActorsRepository> { ActorsRepositoryImpl(remoteDataSource = get(), mapper = get()) }

    viewModel { ActorsListViewModel(actorsRepository = get(), mapper = get()) }
}

internal fun provideActorsService(
    retrofit: Retrofit
): ActorsService = retrofit.create(ActorsService::class.java)