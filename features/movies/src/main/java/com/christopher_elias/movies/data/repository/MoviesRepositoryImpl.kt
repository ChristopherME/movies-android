package com.christopher_elias.movies.data.repository

import com.christopher_elias.functional_programming.Either
import com.christopher_elias.functional_programming.Failure
import com.christopher_elias.movies.data.data_source.MoviesRemoteDataSource
import com.christopher_elias.movies.domain.repository.MoviesRepository
import com.christopher_elias.movies.domain.model.Movie
import com.christopher_elias.movies.mapper.MovieMapper

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

    override suspend fun getMovies(): Either<Failure, List<Movie>> {
        return remoteDataSource.getMovies()
            .coMapSuccess { remoteMovies ->
                mapper.mapRemoteMoviesListToDomain(remoteMovies)
            }
    }
}