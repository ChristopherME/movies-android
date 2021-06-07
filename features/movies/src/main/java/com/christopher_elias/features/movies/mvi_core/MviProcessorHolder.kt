package com.christopher_elias.features.movies.mvi_core

import kotlinx.coroutines.flow.Flow

/*
 * Created by Christopher Elias on 14/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

interface MviProcessorHolder<A : MviAction, R : MviResult> {

    fun processAction(action: A): Flow<R>
}