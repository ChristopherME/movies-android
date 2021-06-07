package com.christopher_elias.features.actors.di

import com.christopher_elias.features.actors.data.data_source.ActorsRemoteDataSource
import com.christopher_elias.features.actors.data.repository.ActorsRepositoryImpl
import com.christopher_elias.features.actors.data_source.remote.ActorsRemoteDataSourceImpl
import com.christopher_elias.features.actors.data_source.remote.retrofit.ActorsService
import com.christopher_elias.features.actors.domain.repository.ActorsRepository
import com.christopher_elias.features.actors.mapper.ActorsMapper
import com.christopher_elias.features.actors.mapper.ActorsMapperImpl
import com.christopher_elias.features.actors.presentation.ui.actors_list.ActorsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

val featureActorsModule = module {

    factory { provideActorsService(retrofit = get()) }

    factory<ActorsRemoteDataSource> {
        ActorsRemoteDataSourceImpl(
            middlewareProvider = get(),
            ioDispatcher = get(named("ioDispatcher")),
            errorAdapter = get(),
            actorsService = get()
        )
    }

    factory<ActorsMapper> {
        ActorsMapperImpl(
            defaultDispatcher = get(named("defaultDispatcher")),
            movieMapper = get(),
            resourceProvider = get()
        )
    }

    factory<ActorsRepository> { ActorsRepositoryImpl(remoteDataSource = get(), mapper = get()) }

    viewModel { ActorsListViewModel(actorsRepository = get(), mapper = get()) }
}

internal fun provideActorsService(
    retrofit: Retrofit
): ActorsService = retrofit.create(ActorsService::class.java)