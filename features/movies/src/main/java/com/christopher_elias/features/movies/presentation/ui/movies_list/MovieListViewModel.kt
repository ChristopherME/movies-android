package com.christopher_elias.features.movies.presentation.ui.movies_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.christopher_elias.features.movies.domain.MoviesRepository
import com.christopher_elias.common.models.mapper.MovieMapper
import com.christopher_elias.common.models.presentation.MovieUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

class MovieListViewModel(
    private val moviesRepository: MoviesRepository,
    private val mapper: MovieMapper
) : ViewModel() {

    fun getMovies(): Flow<PagingData<MovieUi>> {
        return moviesRepository.getMovies()
            .map { pagingData ->
                pagingData.map {
                    mapper.mapDomainMovieToUi(domainMovie = it)
                }
            }
            .cachedIn(viewModelScope)
    }
}