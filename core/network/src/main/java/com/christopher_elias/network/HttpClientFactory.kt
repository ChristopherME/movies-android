package com.christopher_elias.network

import okhttp3.OkHttpClient

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.elias@loop-ideas.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

// This can be Internal. If we move the NetworkModule to here.
class HttpClientFactory {


    private val httpClient by lazy {

        OkHttpClient()
    }

    fun create(): OkHttpClient.Builder {
        return httpClient.newBuilder()
    }
}