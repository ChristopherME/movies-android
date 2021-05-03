package com.christopher_elias.actors.presentation.list

import com.christopher_elias.actors.presentation.model.ActorUi
import com.christopher_elias.functional_programming.Failure
import com.christopher_elias.utils.OneTimeEvent

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.elias@loop-ideas.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

data class ActorsListUiState(
    val isLoading: Boolean = false,
    val actors: List<ActorUi> = emptyList(),
    val error: OneTimeEvent<Failure>? = null
)