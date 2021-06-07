package com.christopher_elias.features.movies.presentation.ui.movies_list.result

import com.christopher_elias.common.models.presentation.MovieUi
import com.christopher_elias.features.movies.mvi_core.MviResult
import com.christopher_elias.functional_programming.Failure

/*
 * Created by Christopher Elias on 14/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

sealed class MovieListResult : MviResult {
    data class Success(val movies: List<MovieUi>) : MovieListResult()
    data class Error(val failure: Failure) : MovieListResult()
    object Loading : MovieListResult()
}