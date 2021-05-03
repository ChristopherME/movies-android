package com.christopher_elias.network.middleware.provider

import com.christopher_elias.network.middleware.NetworkMiddleware

/*
 * Created by Christopher Elias on 3/05/2021
 * christopher.elias@loop-ideas.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

interface MiddlewareProvider {
    fun getAll(): List<NetworkMiddleware>
}