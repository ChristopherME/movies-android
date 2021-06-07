package com.christopher_elias.features.movies.mvi_core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/*
 * Created by Christopher Elias on 14/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

/**
 * Object that will subscribes to a [MviView]'s [MviIntent]s,
 * process it and emit a [MviViewState] back.
 *
 * @param I Top class of the [MviIntent] that the [MviViewModel] will be subscribing
 * to.
 * @param S Top class of the [MviViewState] the [MviViewModel] will be emitting.
 */
interface MviViewModel<I : MviIntent, A : MviAction, S : MviViewState> {

    fun processIntents(intent: I)

    fun mapIntentToAction(intent: I): A

    val uiState: StateFlow<S>
}