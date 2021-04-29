package com.christopher_elias.movies.presentation.movies_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christopher_elias.functional_programming.Failure
import com.christopher_elias.movies.domain.repository.MoviesRepository
import com.christopher_elias.movies.mapper.MovieMapper
import com.christopher_elias.movies.presentation.model.MovieUi
import com.christopher_elias.utils.toOneTimeEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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

    private val _uiState = MutableStateFlow(MovieListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {

            _uiState.value = uiState.value.copy(isLoading = true)

            moviesRepository
                .getMovies()
                .coMapSuccess { domainMovies -> mapper.mapDomainMoviesListToUi(domainMovies) }
                .either(::handleGetMoviesFailure, ::handleGetMoviesSuccess)
        }

    }

    private fun handleGetMoviesSuccess(movies: List<MovieUi>) {
        _uiState.value = uiState.value.copy(
            isLoading = false,
            movies = movies,
            error = null
        )
    }

    private fun handleGetMoviesFailure(failure: Failure) {
        _uiState.value = uiState.value.copy(
            isLoading = false,
            error = failure.toOneTimeEvent()
        )
    }
}