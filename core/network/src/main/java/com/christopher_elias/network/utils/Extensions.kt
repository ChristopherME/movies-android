package com.christopher_elias.network.utils

import com.christopher_elias.functional_programming.Either
import com.christopher_elias.functional_programming.Failure
import com.christopher_elias.functional_programming.toError
import com.christopher_elias.functional_programming.toSuccess
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
 * Loop Ideas
 * Lima, Peru.
 */



/**
 * @param middleWare a customizable middleware that its invoked before executing [retrofitCall]
 * @param middleWareFailure the failure to throw if the [middleWare] fails.
 * @param ioDispatcher the [CoroutineDispatcher] which is expected to be a Dispatcher.IO for make a safe call.
 * @param adapter the adapter to provide in order to parse the errors from the service.
 * @param retrofitCall the block to invoke the retrofit method.
 */
suspend inline fun <T> call(
    crossinline middleWare: () -> Boolean = { true },
    middleWareFailure: NetworkMiddlewareFailure = NetworkMiddlewareFailure("Middleware condition fail"),
    ioDispatcher: CoroutineDispatcher,
    adapter: JsonAdapter<ResponseError>,
    crossinline retrofitCall: suspend () -> T
): Either<Failure, T> {
    if (middleWare.invoke()) {
        return withContext(ioDispatcher) {
            try {
                return@withContext retrofitCall().toSuccess()
            } catch (e: Exception) {
                return@withContext e.parseException(adapter).toError()
            }
        }
    } else {
        return middleWareFailure.toError()
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