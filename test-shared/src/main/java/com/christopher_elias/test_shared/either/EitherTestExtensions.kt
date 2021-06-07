package com.christopher_elias.test_shared.either

import com.christopher_elias.functional_programming.Either

/*
 * Created by Christopher Elias on 22/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

/**
 * @param block the block to ve invoked if the result of the [Either] instance is a [Either.Success]
 * @throws [EitherTestException] if the result of the [Either] instance is [Either.Error]
 */
inline fun <L, R> Either<L, R>.getDataWhenResultIsSuccessOrThrowException(
    block: (someResult: R) -> Unit
) {
    if (isSuccess) {
        block((this as Either.Success<R>).success)
    } else {
        throw EitherTestException("The result is a Failure. ${(this as Either.Error<L>).error}")
    }
}

/**
 * @param block the block to ve invoked if the result of the [Either] instance is a [Either.Error]
 * @throws [EitherTestException] if the result of the [Either] instance is [Either.Success]
 */
inline fun <L, R> Either<L, R>.getDataWhenResultIsFailureOrThrowException(
    block: (someError: L) -> Unit
) {
    if (isError) {
        block((this as Either.Error<L>).error)
    } else {
        throw EitherTestException("The result is Success. ${(this as Either.Success<R>).success}")
    }
}