package com.christopher_elias.features.actors.data_source.remote

import com.christopher_elias.features.actors.data.data_source.ActorsRemoteDataSource
import com.christopher_elias.features.actors.data_source.models.ActorsResponse
import com.christopher_elias.features.actors.data_source.remote.retrofit.ActorsService
import com.christopher_elias.functional_programming.Either
import com.christopher_elias.functional_programming.Failure
import com.christopher_elias.network.middleware.provider.MiddlewareProvider
import com.christopher_elias.network.models.base.ResponseError
import com.christopher_elias.network.utils.call
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.CoroutineDispatcher

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

internal class ActorsRemoteDataSourceImpl(
    private val middlewareProvider: MiddlewareProvider,
    private val ioDispatcher: CoroutineDispatcher,
    private val errorAdapter: JsonAdapter<ResponseError>,
    private val actorsService: ActorsService
) : ActorsRemoteDataSource {

    override suspend fun getActors(): Either<Failure, List<ActorsResponse>> {
        return call(
            middleWares = middlewareProvider.getAll(),
            ioDispatcher = ioDispatcher,
            adapter = errorAdapter,
            retrofitCall = {
                actorsService.getActors(
                    language = "en-US",
                    page = 1
                )
            }
        ).let { response -> response.mapSuccess { responseItems -> responseItems.results } }
    }
}