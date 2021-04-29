package com.christopher_elias.movies.data.data_source

import com.christopher_elias.functional_programming.Either
import com.christopher_elias.functional_programming.Failure
import com.christopher_elias.movies.data_source.model.MovieResponse

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

interface MoviesRemoteDataSource {

    suspend fun getMovies(): Either<Failure, List<MovieResponse>>
}