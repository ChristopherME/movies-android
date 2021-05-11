package com.christopher_elias.features.movies.data_source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.christopher_elias.features.movies.data.data_source.MoviesRemoteDataSource
import com.christopher_elias.common.models.data.MovieResponse
import com.christopher_elias.features.movies.data_source.remote.retrofit_service.MovieService
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */


const val NETWORK_PAGE_SIZE = 25

internal class MoviesRemoteDataSourceImpl(
    private val movieService: MovieService
) : MoviesRemoteDataSource {

    override fun getMovies(): Flow<PagingData<MovieResponse>> {
        Timber.d("Request movies")
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviesPagingSource(service = movieService)
            }
        ).flow
    }
}