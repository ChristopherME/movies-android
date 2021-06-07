package com.christopher_elias.movies.middlewares

import com.christopher_elias.movies.R
import com.christopher_elias.network.middleware.NetworkMiddleware
import com.christopher_elias.network.models.exception.NetworkMiddlewareFailure
import com.christopher_elias.utils.resource_provider.ResourceProvider
import com.christopher_elias.utils.connectivity.ConnectivityUtils

/*
 * Created by Christopher Elias on 3/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class ConnectivityMiddleware(
    private val connectivityUtils: ConnectivityUtils,
    private val resourceProvider: ResourceProvider
) : NetworkMiddleware() {

    override val failure: NetworkMiddlewareFailure
        get() = NetworkMiddlewareFailure(
            middleWareExceptionMessage = resourceProvider.getString(R.string.error_no_network_detected)
        )

    override fun isValid(): Boolean = connectivityUtils.isNetworkAvailable()
}