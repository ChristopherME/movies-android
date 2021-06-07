package com.christopher_elias.features.movies.presentation.ui.movies_list.intent

import com.christopher_elias.features.movies.mvi_core.MviIntent

/*
 * Created by Christopher Elias on 14/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

sealed class MovieListIntent : MviIntent {

    /**
     * When the user enters the screen (In this case this is also an intent as we need to trigger the Load movies action).
     */
    object InitialIntent : MovieListIntent()

    object SwipeOnRefresh : MovieListIntent()
}