package com.christopher_elias.network.middleware.provider

import com.christopher_elias.network.middleware.NetworkMiddleware

/*
 * Created by Christopher Elias on 3/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

interface MiddlewareProvider {
    fun getAll(): List<NetworkMiddleware>
}