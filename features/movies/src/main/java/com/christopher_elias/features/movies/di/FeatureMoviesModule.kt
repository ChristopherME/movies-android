package com.christopher_elias.features.movies.di

import com.christopher_elias.features.movies.data.data_source.MoviesRemoteDataSource
import com.christopher_elias.features.movies.data.repository.MoviesRepositoryImpl
import com.christopher_elias.features.movies.data_source.remote.MoviesRemoteDataSourceImpl
import com.christopher_elias.features.movies.data_source.remote.retrofit_service.MovieService
import com.christopher_elias.features.movies.domain.repository.MoviesRepository
import com.christopher_elias.features.movies.mapper.MovieMapper
import com.christopher_elias.features.movies.mapper.MovieMapperImpl
import com.christopher_elias.features.movies.presentation.ui.movies_list.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

val featureMoviesModule = module {

    factory { provideMoviesService(retrofit = get()) }

    factory<MoviesRemoteDataSource> {
        MoviesRemoteDataSourceImpl(
            middlewareProvider = get(),
            ioDispatcher = get(named("ioDispatcher")),
            adapter = get(),
            movieService = get(),
            tmdbKey = get(named("TMDB_KEY"))
        )
    }

    /**
     * TODO: Improve this.
     * This is not so optimal due to viewModel is requiring also the mapper,
     * Therefore a new instance on movieMapper is going to be created.
     * We ended up with 2 instances of MovieMapper.
     */
    factory<MovieMapper> { MovieMapperImpl(defaultDispatcher = get(named("defaultDispatcher"))) }

    factory<MoviesRepository> { MoviesRepositoryImpl(remoteDataSource = get(), mapper = get()) }

    viewModel { MovieListViewModel(moviesRepository = get(), mapper = get()) }

}

internal fun provideMoviesService(
    retrofit: Retrofit
): MovieService = retrofit.create(MovieService::class.java)