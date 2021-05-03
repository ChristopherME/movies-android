package com.christopher_elias.movies.middlewares.provider

import com.christopher_elias.movies.middlewares.ConnectivityMiddleware
import com.christopher_elias.network.middleware.NetworkMiddleware
import com.christopher_elias.network.middleware.provider.MiddlewareProvider

/*
 * Created by Christopher Elias on 3/05/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

class MiddlewareProviderImpl(
    private val connectivityMiddleware: ConnectivityMiddleware
) : MiddlewareProvider {

    private val middlewareList: List<NetworkMiddleware> by lazy {
        listOf(connectivityMiddleware)
    }

    override fun getAll(): List<NetworkMiddleware> = middlewareList
}