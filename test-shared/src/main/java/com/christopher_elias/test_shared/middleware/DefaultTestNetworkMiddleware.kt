package com.christopher_elias.test_shared.middleware

import com.christopher_elias.network.middleware.NetworkMiddleware
import com.christopher_elias.network.models.exception.NetworkMiddlewareFailure

/*
 * Created by Christopher Elias on 3/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class DefaultTestNetworkMiddleware(
    private val isMiddlewareValid: Boolean,
    private val failureMessage: String = ""
) : NetworkMiddleware() {

    override val failure: NetworkMiddlewareFailure
        get() = NetworkMiddlewareFailure(middleWareExceptionMessage = failureMessage)

    override fun isValid(): Boolean = isMiddlewareValid

}