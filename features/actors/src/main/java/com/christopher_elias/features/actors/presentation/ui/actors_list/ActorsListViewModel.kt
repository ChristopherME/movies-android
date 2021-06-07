package com.christopher_elias.features.actors.presentation.ui.actors_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christopher_elias.features.actors.domain.repository.ActorsRepository
import com.christopher_elias.features.actors.mapper.ActorsMapper
import com.christopher_elias.features.actors.presentation.ui.actors_list.state.ActorsListUiState
import com.christopher_elias.features.actors.presentation.model.ActorUi
import com.christopher_elias.functional_programming.Failure
import com.christopher_elias.utils.toOneTimeEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class ActorsListViewModel(
    private val actorsRepository: ActorsRepository,
    private val mapper: ActorsMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow(ActorsListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getActors()
    }

    private fun getActors() {
        viewModelScope.launch {

            _uiState.value = uiState.value.copy(isLoading = true)

            actorsRepository
                .getActors()
                .coMapSuccess { domainActors -> mapper.mapDomainActorsToUi(domainActors) }
                .either(::handleGetActorsFailure, ::handleGetActorsSuccess)
        }

    }

    private fun handleGetActorsSuccess(actors: List<ActorUi>) {
        _uiState.value = uiState.value.copy(
            isLoading = false,
            actors = actors,
            error = null
        )
    }

    private fun handleGetActorsFailure(failure: Failure) {
        _uiState.value = uiState.value.copy(
            isLoading = false,
            error = failure.toOneTimeEvent()
        )
    }
}