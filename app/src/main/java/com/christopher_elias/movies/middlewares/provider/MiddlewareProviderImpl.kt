package com.christopher_elias.movies.middlewares.provider

import com.christopher_elias.network.middleware.NetworkMiddleware
import com.christopher_elias.network.middleware.provider.MiddlewareProvider

/*
 * Created by Christopher Elias on 3/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class MiddlewareProviderImpl private constructor(
    private val middlewareList: List<NetworkMiddleware> = listOf()
) : MiddlewareProvider {

    class Builder(
        private val middlewareList: MutableList<NetworkMiddleware> = mutableListOf()
    ) {

        fun add(middleware: NetworkMiddleware) = apply {
            this.middlewareList.add(middleware)
        }

        fun build() = MiddlewareProviderImpl(
            middlewareList = middlewareList
        )
    }


    override fun getAll(): List<NetworkMiddleware> = middlewareList
}