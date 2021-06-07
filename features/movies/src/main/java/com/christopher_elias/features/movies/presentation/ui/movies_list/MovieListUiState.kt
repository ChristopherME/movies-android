package com.christopher_elias.features.movies.presentation.ui.movies_list

import com.christopher_elias.functional_programming.Failure
import com.christopher_elias.common.models.presentation.MovieUi
import com.christopher_elias.features.movies.mvi_core.MviViewState
import com.christopher_elias.utils.OneTimeEvent

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

/**
 * Holds the state to represent in MovieListFragment.
 */
data class MovieListUiState(
    val isLoading: Boolean = false,
    val movies: List<MovieUi> = emptyList(),
    val error: OneTimeEvent<Failure>? = null
) : MviViewState