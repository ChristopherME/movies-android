package com.christopher_elias.movies.di

import com.christopher_elias.movies.middlewares.ConnectivityMiddleware
import com.christopher_elias.movies.middlewares.provider.MiddlewareProviderImpl
import com.christopher_elias.network.middleware.provider.MiddlewareProvider
import org.koin.dsl.module

/*
 * Created by Christopher Elias on 3/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

val middleWareModule = module {

    // Singleton instance of middleware provider.
    single<MiddlewareProvider> {
        MiddlewareProviderImpl.Builder()
            .add(
                middleware = ConnectivityMiddleware(
                    connectivityUtils = get(),
                    resourceProvider = get()
                )
            )
            .build()
    }
}