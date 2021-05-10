package com.christopher_elias.features.movies.domain

import androidx.paging.PagingData
import com.christopher_elias.common.models.domain.Movie
import kotlinx.coroutines.flow.Flow

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

interface MoviesRepository {

    fun getMovies(): Flow<PagingData<Movie>>
}