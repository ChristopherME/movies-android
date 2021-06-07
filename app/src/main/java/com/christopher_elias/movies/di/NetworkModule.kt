package com.christopher_elias.movies.di

import com.christopher_elias.movies.BuildConfig
import com.christopher_elias.network.HttpClientFactory
import com.christopher_elias.network.models.base.ResponseError
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */


val networkModule = module {
    single { HttpClientFactory() }
    single { provideOkHttpBuilder(httpClientFactory = get()) }
    single { provideClient(clientBuilder = get(), apiKey = get(named("TMDB_KEY"))) }
    single { provideRetrofitBuilder(okHttpClient = get()) }
    single { provideMoshi() }
    single { provideJsonErrorAdapter(moshi = get()) }
}

internal fun provideOkHttpBuilder(
    httpClientFactory: HttpClientFactory
): OkHttpClient.Builder {
    return httpClientFactory.create()
}

internal fun provideClient(
    clientBuilder: OkHttpClient.Builder,
    apiKey: String
): OkHttpClient {
    clientBuilder
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
            val originalHttpUrl = chain.request().url
            val url = originalHttpUrl.newBuilder().addQueryParameter("api_key", apiKey).build()
            request.url(url)
            return@addInterceptor chain.proceed(request.build())
        }
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(loggingInterceptor)
    }
    return clientBuilder.build()
}

fun provideRetrofitBuilder(
    okHttpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

internal fun provideMoshi(): Moshi {
    return Moshi.Builder().build()
}


fun provideJsonErrorAdapter(moshi: Moshi): JsonAdapter<ResponseError> {
    return moshi.adapter(ResponseError::class.java)
}