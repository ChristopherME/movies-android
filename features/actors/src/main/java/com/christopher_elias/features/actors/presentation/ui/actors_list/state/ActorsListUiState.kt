package com.christopher_elias.features.actors.presentation.ui.actors_list.state

import com.christopher_elias.features.actors.presentation.model.ActorUi
import com.christopher_elias.functional_programming.Failure
import com.christopher_elias.utils.OneTimeEvent

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

data class ActorsListUiState(
    val isLoading: Boolean = false,
    val actors: List<ActorUi> = emptyList(),
    val error: OneTimeEvent<Failure>? = null
)