package com.christopher_elias.network

import com.christopher_elias.network.middleware.NetworkMiddleware
import com.christopher_elias.network.models.exception.NetworkMiddlewareFailure

/*
 * Created by Christopher Elias on 3/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class DumbMiddleware(
    private val hardCodedValidation: Boolean,
    private val middlewareFailureMessage: String
) : NetworkMiddleware() {

    override val failure: NetworkMiddlewareFailure
        get() = NetworkMiddlewareFailure(middleWareExceptionMessage = middlewareFailureMessage)

    override fun isValid(): Boolean = hardCodedValidation
}

class AnotherDumbMiddleware() : NetworkMiddleware() {
    override val failure: NetworkMiddlewareFailure
        get() = NetworkMiddlewareFailure("")

    override fun isValid(): Boolean = true

}