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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

@ExperimentalCoroutinesApi
class MovieListViewModel(
    private val actionProcessorHolder: MovieListProcessorHolder
) : ViewModel(), MviViewModel<MovieListIntent, MovieListAction, MovieListUiState> {

    private val _uiState = MutableStateFlow(MovieListUiState())

    override val uiState: StateFlow<MovieListUiState>
        get() = _uiState.asStateFlow()

    override fun processIntents(intents: Flow<MovieListIntent>) {
        viewModelScope.launch {
            intents
                .map { intent -> mapIntentToAction(intent = intent) }
                .flatMapLatest { action -> actionProcessorHolder.processAction(action) }
                .collect { result -> reduce(result) }
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
}