package com.christopher_elias.features.movies.presentation.ui.movies_list.action

import com.christopher_elias.features.movies.mvi_core.MviAction

/*
 * Created by Christopher Elias on 14/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

sealed class MovieListAction : MviAction {
    object LoadMoviesAction : MovieListAction()
}