package com.christopher_elias.features.movies.presentation.ui.movies_list

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/*
 * Created by Christopher Elias on 14/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

fun SwipeRefreshLayout.flowRefresh(): Flow<Unit> = callbackFlow {
    var listener: SwipeRefreshLayout.OnRefreshListener? = SwipeRefreshLayout.OnRefreshListener {
        offer(Unit)
    }

    setOnRefreshListener(listener)

    awaitClose {
        listener = null
        setOnRefreshListener(null)
    }
}