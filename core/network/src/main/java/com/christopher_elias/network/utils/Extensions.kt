package com.christopher_elias.network.utils

import com.christopher_elias.functional_programming.Either
import com.christopher_elias.functional_programming.Failure
import com.christopher_elias.functional_programming.utils.toError
import com.christopher_elias.functional_programming.utils.toSuccess
import com.christopher_elias.network.middleware.NetworkMiddleware
import com.christopher_elias.network.models.base.ResponseError
import com.christopher_elias.network.models.exception.*
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okio.BufferedSource
import retrofit2.HttpException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException

/*
 * Created by Christopher Elias on 22/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */



/**
 * @param middleWares list of customizable [NetworkMiddleware] that would returns its error if one of them is not valid.
 * @param ioDispatcher the [CoroutineDispatcher] which is expected to be a Dispatcher.IO for make a safe call.
 * @param adapter the adapter to provide in order to parse the errors from the service.
 * @param retrofitCall the block to invoke the retrofit method.
 */
suspend inline fun <T> call(
    middleWares: List<NetworkMiddleware> = emptyList(),
    ioDispatcher: CoroutineDispatcher,
    adapter: JsonAdapter<ResponseError>,
    crossinline retrofitCall: suspend () -> T
): Either<Failure, T> {
    return runMiddleWares(middleWares = middleWares)?.toError()
        ?: executeRetrofitCall(ioDispatcher, adapter, retrofitCall)
}

/**
 * Iterate ove all the [NetworkMiddleware] and return true if all of them are valid.
 * @return []
 */
fun runMiddleWares(
    middleWares: List<NetworkMiddleware> = emptyList(),
): Failure? {
    if (middleWares.isEmpty()) return null
    return middleWares.find { !it.isValid() }?.failure
}

suspend inline fun <T> executeRetrofitCall(
    ioDispatcher: CoroutineDispatcher,
    adapter: JsonAdapter<ResponseError>,
    crossinline retrofitCall: suspend () -> T
): Either<Failure, T> {
    return withContext(ioDispatcher) {
        try {
            return@withContext retrofitCall().toSuccess()
        } catch (e: Exception) {
            return@withContext e.parseException(adapter).toError()
        }
    }
}

fun Throwable.parseException(
    adapter: JsonAdapter<ResponseError>
): Failure {
    return when (this) {
        is SocketTimeoutException -> TimeOut
        is SSLException -> NetworkConnectionLostSuddenly
        is SSLHandshakeException -> SSLError
        is HttpException -> {
            val errorService = adapter.parseError(response()?.errorBody()?.source())
            if (errorService != null) {
                ServiceBodyFailure(
                    internalCode = errorService.status,
                    internalMessage = errorService.message
                )
            } else {
                Failure.UnexpectedFailure(
                    message = "Service ERROR BODY does not match."
                )
            }
        }
        else -> Failure.UnexpectedFailure(
            message = message ?: "Exception not handled caused an Unknown failure"
        )
    }
}

private fun JsonAdapter<ResponseError>.parseError(
    json: BufferedSource?
): ResponseError? {
    return if (json != null) {
        fromJson(json)
    } else {
        null
    }
}