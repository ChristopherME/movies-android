package com.christopher_elias.features.movies.di

import com.christopher_elias.features.movies.data.data_source.MoviesRemoteDataSource
import com.christopher_elias.features.movies.data.repository.MoviesRepositoryImpl
import com.christopher_elias.features.movies.data_source.remote.MoviesRemoteDataSourceImpl
import com.christopher_elias.features.movies.data_source.remote.retrofit_service.MovieService
import com.christopher_elias.features.movies.domain.MoviesRepository
import com.christopher_elias.features.movies.presentation.ui.movies_list.MovieListViewModel
import com.christopher_elias.features.movies.presentation.ui.movies_list.processor.MovieListProcessorHolder
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

val featureMoviesModule = module {

    factory { provideMoviesService(retrofit = get()) }

    factory<MoviesRemoteDataSource> {
        MoviesRemoteDataSourceImpl(
            middlewareProvider = get(),
            ioDispatcher = get(named("ioDispatcher")),
            adapter = get(),
            movieService = get()
        )
    }

    factory<MoviesRepository> { MoviesRepositoryImpl(remoteDataSource = get(), mapper = get()) }

    factory { MovieListProcessorHolder(moviesRepository = get(), moviesMapper = get()) }

    viewModel { MovieListViewModel(actionProcessorHolder = get()) }

}

internal fun provideMoviesService(
    retrofit: Retrofit
): MovieService = retrofit.create(MovieService::class.java)