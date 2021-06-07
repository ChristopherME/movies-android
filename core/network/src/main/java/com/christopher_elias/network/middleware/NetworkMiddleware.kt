package com.christopher_elias.network.middleware

import com.christopher_elias.network.models.exception.NetworkMiddlewareFailure

/*
 * Created by Christopher Elias on 3/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

abstract class NetworkMiddleware {

    abstract val failure: NetworkMiddlewareFailure

    abstract fun isValid(): Boolean
}