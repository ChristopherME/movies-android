package com.christopher_elias.features.movies.presentation.ui.movies_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christopher_elias.features.movies.mvi_core.MviViewModel
import com.christopher_elias.features.movies.presentation.ui.movies_list.action.MovieListAction
import com.christopher_elias.features.movies.presentation.ui.movies_list.intent.MovieListIntent
import com.christopher_elias.features.movies.presentation.ui.movies_list.processor.MovieListProcessorHolder
import com.christopher_elias.features.movies.presentation.ui.movies_list.result.MovieListResult
import com.christopher_elias.utils.toOneTimeEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@ExperimentalCoroutinesApi
class MovieListViewModel(
    private val actionProcessorHolder: MovieListProcessorHolder
) : ViewModel(), MviViewModel<MovieListIntent, MovieListAction, MovieListUiState> {

    private val _uiState = MutableStateFlow(MovieListUiState())

    override val uiState: StateFlow<MovieListUiState>
        get() = _uiState.asStateFlow()

    // Im not using ConflatedBroadcastChannel here because I don't need multiple subscribers.
    // Probably I have to rename this variable as could be kinda confusing...
    private val actions = Channel<MovieListAction>()

    init {

        // Trigger the initial intent only once.
        // TODO: Create some flow filter that only takes the InitialIntent once,
        //  With that we can remove this line and emit the initial intent from the fragment.
        processIntents(MovieListIntent.InitialIntent)

        // Subscribe to Actions
        subscribeActions()
    }

    override fun processIntents(intent: MovieListIntent) {
        viewModelScope.launch {
            actions.send(mapIntentToAction(intent = intent))
        }
    }

    override fun mapIntentToAction(intent: MovieListIntent): MovieListAction {
        return when (intent) {
            MovieListIntent.InitialIntent -> MovieListAction.LoadMoviesAction
            MovieListIntent.SwipeOnRefresh -> MovieListAction.LoadMoviesAction
        }
    }

    private fun reduce(result: MovieListResult) {
        when (result) {
            is MovieListResult.Success -> {
                _uiState.value = uiState.value.copy(
                    isLoading = false,
                    movies = result.movies,
                    error = null
                )
            }
            is MovieListResult.Error -> {
                _uiState.value = uiState.value.copy(
                    isLoading = false,
                    error = result.failure.toOneTimeEvent()
                )
            }
            MovieListResult.Loading -> {
                _uiState.value = uiState.value.copy(isLoading = true)
            }
        }
    }

    private fun subscribeActions() {
        viewModelScope.launch {
            actions.receiveAsFlow()
                .flatMapLatest { actionProcessorHolder.processAction(action = it) }
                .collectLatest { reduce(it) }
        }
    }
}