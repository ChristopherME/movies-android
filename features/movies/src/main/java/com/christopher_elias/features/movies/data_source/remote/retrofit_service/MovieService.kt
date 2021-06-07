package com.christopher_elias.features.movies.data_source.remote.retrofit_service

import com.christopher_elias.common.models.data.MovieResponse
import com.christopher_elias.network.models.base.ResponseItems
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

internal interface MovieService {

    /**
     * @return List of [MovieResponse]
     *
     * @param apiKey API KEY from TMDB. REPLACE this value with your KEY or add some authentication.
     * @param language the language to obtain the data.
     * @param page the current page of items.
     */
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): ResponseItems<MovieResponse>
}