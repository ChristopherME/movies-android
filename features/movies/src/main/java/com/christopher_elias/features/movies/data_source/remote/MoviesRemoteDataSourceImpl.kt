package com.christopher_elias.features.movies.data_source.remote

import com.christopher_elias.functional_programming.Either
import com.christopher_elias.functional_programming.Failure
import com.christopher_elias.features.movies.data.data_source.MoviesRemoteDataSource
import com.christopher_elias.common.models.data.MovieResponse
import com.christopher_elias.features.movies.data_source.remote.retrofit_service.MovieService
import com.christopher_elias.network.middleware.provider.MiddlewareProvider
import com.christopher_elias.network.models.base.ResponseError
import com.christopher_elias.network.utils.call
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.CoroutineDispatcher

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */


internal class MoviesRemoteDataSourceImpl(
    private val middlewareProvider: MiddlewareProvider,
    private val ioDispatcher: CoroutineDispatcher,
    private val adapter: JsonAdapter<ResponseError>,
    private val movieService: MovieService
) : MoviesRemoteDataSource {

    override suspend fun getMovies(): Either<Failure, List<MovieResponse>> {
        return call(
            middleWares = middlewareProvider.getAll(),
            ioDispatcher = ioDispatcher,
            adapter = adapter,
            retrofitCall = {
                movieService.getTopRatedMovies(
                    language = "en-US",
                    page = 1
                )
            }
        ).let { response -> response.mapSuccess { responseItems -> responseItems.results } }
    }
}