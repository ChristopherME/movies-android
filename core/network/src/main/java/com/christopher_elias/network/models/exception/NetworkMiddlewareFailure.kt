package com.christopher_elias.network.models.exception

import com.christopher_elias.functional_programming.Failure

/*
 * Created by Christopher Elias on 22/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class NetworkMiddlewareFailure(
    val middleWareExceptionMessage: String,
) : Failure.CustomFailure()

object TimeOut : Failure.CustomFailure()

object NetworkConnectionLostSuddenly : Failure.CustomFailure()

object SSLError : Failure.CustomFailure()

/**
 * If your service return some custom error use this with the given attars you expect.
 */
data class ServiceBodyFailure(
    val internalCode: Int,
    val internalMessage: String?
) : Failure.CustomFailure()
