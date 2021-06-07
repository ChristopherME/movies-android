package com.christopher_elias.features.movies.data

import com.christopher_elias.common.models.data.MovieResponse
import com.christopher_elias.network.models.base.ResponseItems
import com.christopher_elias.test_shared.file.FileReaderUtil
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

/*
 * Created by Christopher Elias on 27/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

object MoviesData {

    private val moshi = Moshi.Builder().build()
    private val moviesResponseGenericType: Type = Types.newParameterizedType(
        ResponseItems::class.java,
        MovieResponse::class.java
    )
    private val remoteMoviesAdapter: JsonAdapter<ResponseItems<MovieResponse>> =
        moshi.adapter(moviesResponseGenericType)

    fun provideRemoteMoviesFromAssets(): List<MovieResponse> {
        return remoteMoviesAdapter.fromJson(
            FileReaderUtil.kotlinReadFileWithNewLineFromResources(
                fileName = "movies.json"
            )
        )?.results ?: listOf()
    }
}