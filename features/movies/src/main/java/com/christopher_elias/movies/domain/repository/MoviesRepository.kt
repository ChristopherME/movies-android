package com.christopher_elias.movies.domain.repository

import com.christopher_elias.functional_programming.Either
import com.christopher_elias.functional_programming.Failure
import com.christopher_elias.movies.domain.model.Movie

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

interface MoviesRepository {

    suspend fun getMovies(): Either<Failure, List<Movie>>
}