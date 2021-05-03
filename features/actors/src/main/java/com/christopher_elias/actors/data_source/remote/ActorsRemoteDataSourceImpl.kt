package com.christopher_elias.actors.data_source.remote

import com.christopher_elias.actors.data.data_source.ActorsRemoteDataSource
import com.christopher_elias.actors.data_source.models.ActorsResponse
import com.christopher_elias.actors.data_source.remote.retrofit.ActorsService
import com.christopher_elias.functional_programming.Either
import com.christopher_elias.functional_programming.Failure
import com.christopher_elias.network.models.base.ResponseError
import com.christopher_elias.network.models.exception.NetworkMiddlewareFailure
import com.christopher_elias.network.utils.call
import com.christopher_elias.utils.connectivity.ConnectivityUtils
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.CoroutineDispatcher

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.elias@loop-ideas.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

internal class ActorsRemoteDataSourceImpl(
    private val connectivityUtils: ConnectivityUtils,
    private val ioDispatcher: CoroutineDispatcher,
    private val errorAdapter: JsonAdapter<ResponseError>,
    private val actorsService: ActorsService,
    private val tmdbKey: String,
) : ActorsRemoteDataSource {

    override suspend fun getActors(): Either<Failure, List<ActorsResponse>> {
        return call(
            middleWare = { connectivityUtils.isNetworkAvailable() },
            middleWareFailure = NetworkMiddlewareFailure(middleWareExceptionMessage = "No network detected"),
            ioDispatcher = ioDispatcher,
            adapter = errorAdapter,
            retrofitCall = {
                actorsService.getActors(
                    apiKey = tmdbKey,
                    language = "en-US",
                    page = 1
                )
            }
        ).let { response -> response.mapSuccess { responseItems -> responseItems.results } }
    }
}