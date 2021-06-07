package com.christopher_elias.functional_programming.utils

import com.christopher_elias.functional_programming.Either

/*
 * Created by Christopher Elias on 22/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

fun <R> R.toSuccess(): Either.Success<R> {
    return Either.Success(this)
}

fun <L> L.toError(): Either.Error<L> {
    return Either.Error(this)
}
 