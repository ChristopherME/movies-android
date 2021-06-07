package com.christopher_elias.features.movies.data.data_source

import com.christopher_elias.functional_programming.Either
import com.christopher_elias.functional_programming.Failure
import com.christopher_elias.common.models.data.MovieResponse

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

interface MoviesRemoteDataSource {

    suspend fun getMovies(): Either<Failure, List<MovieResponse>>
}