package com.christopher_elias.features.movies.data.data_source

import androidx.paging.PagingData
import com.christopher_elias.common.models.data.MovieResponse
import kotlinx.coroutines.flow.Flow

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

interface MoviesRemoteDataSource {

    fun getMovies(): Flow<PagingData<MovieResponse>>
}