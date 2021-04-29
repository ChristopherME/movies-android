package com.christopher_elias.functional_programming

/*
 * Created by Christopher Elias on 22/04/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

fun <R> R.toSuccess(): Either.Success<R> {
    return Either.Success(this)
}

fun <L> L.toError(): Either.Error<L> {
    return Either.Error(this)
}
 