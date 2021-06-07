package com.christopher_elias.features.movies.mvi_core

import kotlinx.coroutines.flow.Flow

/*
 * Created by Christopher Elias on 14/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

/**
 * Object representing a UI that will
 * a) emit its intents to a view model,
 * b) subscribes to a view model for rendering its UI.
 *
 * @param I Top class of the [MviIntent] that the [MviView] will be emitting.
 * @param S Top class of the [MviViewState] the [MviView] will be subscribing to.
 */
interface MviView<I : MviIntent, in S : MviViewState> {
    /**
     * Unique [Flow] used by the [MviViewModel]
     * to listen to the [MviView].
     * All the [MviView]'s [MviIntent]s must go through this [Flow].
     */
    fun intents(): Flow<I>

    /**
     * Entry point for the [MviView] to render itself based on a [MviViewState].
     */
    fun render(state: S)
}