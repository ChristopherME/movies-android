package com.christopher_elias.features.movies.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.christopher_elias.features.movies.data.data_source.MoviesRemoteDataSource
import com.christopher_elias.features.movies.domain.MoviesRepository
import com.christopher_elias.common.models.domain.Movie
import com.christopher_elias.common.models.mapper.MovieMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

internal class MoviesRepositoryImpl(
    private val remoteDataSource: MoviesRemoteDataSource,
    private val mapper: MovieMapper
) : MoviesRepository {

    override fun getMovies(): Flow<PagingData<Movie>> {
        return remoteDataSource.getMovies()
            .map { pagingData ->
                pagingData.map { remoteMovie ->
                    mapper.mapRemoteMovieToDomain(remoteMovie = remoteMovie)
                }
            }
    }
}