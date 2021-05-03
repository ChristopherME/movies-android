package com.christopher_elias.movies.data_source.remote

import com.christopher_elias.functional_programming.Either
import com.christopher_elias.functional_programming.Failure
import com.christopher_elias.movies.data.data_source.MoviesRemoteDataSource
import com.christopher_elias.movies.data_source.model.MovieResponse
import com.christopher_elias.movies.data_source.remote.retrofit_service.MovieService
import com.christopher_elias.network.models.base.ResponseError
import com.christopher_elias.network.models.exception.NetworkMiddlewareFailure
import com.christopher_elias.network.utils.call
import com.christopher_elias.utils.connectivity.ConnectivityUtils
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.CoroutineDispatcher

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */


internal class MoviesRemoteDataSourceImpl(
    private val connectivityUtils: ConnectivityUtils,
    private val ioDispatcher: CoroutineDispatcher,
    private val adapter: JsonAdapter<ResponseError>,
    private val movieService: MovieService,
    private val tmdbKey: String
) : MoviesRemoteDataSource {

    override suspend fun getMovies(): Either<Failure, List<MovieResponse>> {
        return call(
            middleWare = { connectivityUtils.isNetworkAvailable() },
            middleWareFailure = NetworkMiddlewareFailure(middleWareExceptionMessage = "No network detected"),
            ioDispatcher = ioDispatcher,
            adapter = adapter,
            retrofitCall = {
                movieService.getTopRatedMovies(
                    apiKey = tmdbKey,
                    language = "en-US",
                    page = 1
                )
            }
        ).let { response -> response.mapSuccess { responseItems -> responseItems.results } }
    }
}