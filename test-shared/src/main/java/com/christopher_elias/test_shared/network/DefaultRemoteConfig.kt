package com.christopher_elias.test_shared.network

import com.christopher_elias.network.models.base.ResponseError
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

/*
 * Created by Christopher Elias on 3/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

object DefaultRemoteConfig {

    private val moshi = Moshi.Builder().build()

    fun provideRemoteErrorAdapter(): JsonAdapter<ResponseError> =
        moshi.adapter(ResponseError::class.java)
}