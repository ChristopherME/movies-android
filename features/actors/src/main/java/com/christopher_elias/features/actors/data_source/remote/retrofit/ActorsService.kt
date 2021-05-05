package com.christopher_elias.features.actors.data_source.remote.retrofit

import com.christopher_elias.features.actors.data_source.models.ActorsResponse
import com.christopher_elias.network.models.base.ResponseItems
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

internal interface ActorsService {

    @GET("person/popular")
    suspend fun getActors(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): ResponseItems<ActorsResponse>
}