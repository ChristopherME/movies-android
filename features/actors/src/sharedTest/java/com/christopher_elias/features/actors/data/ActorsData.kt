package com.christopher_elias.features.actors.data

import com.christopher_elias.features.actors.data_source.models.ActorsResponse
import com.christopher_elias.network.models.base.ResponseItems
import com.christopher_elias.test_shared.file.FileReaderUtil
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

/*
 * Created by Christopher Elias on 3/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

object ActorsData {

    private val moshi = Moshi.Builder().build()
    private val moviesResponseGenericType: Type = Types.newParameterizedType(
        ResponseItems::class.java,
        ActorsResponse::class.java
    )
    private val remoteMoviesAdapter: JsonAdapter<ResponseItems<ActorsResponse>> =
        moshi.adapter(moviesResponseGenericType)

    fun provideRemoteActorsFromAssets(): List<ActorsResponse> {
        return remoteMoviesAdapter.fromJson(
            FileReaderUtil.kotlinReadFileWithNewLineFromResources(
                fileName = "actors.json"
            )
        )?.results ?: listOf()
    }
}