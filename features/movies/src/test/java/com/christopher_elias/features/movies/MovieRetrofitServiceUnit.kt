package com.christopher_elias.features.movies

import com.christopher_elias.features.movies.data.MoviesData
import com.christopher_elias.features.movies.data_source.remote.retrofit_service.MovieService
import com.christopher_elias.test_shared.file.FileReaderUtil
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/*
 * Created by Christopher Elias on 27/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class MovieRetrofitServiceUnit {

    //This will test if our data classes are well mapped with the expected response.
    private val mockWebServer = MockWebServer()
    private lateinit var movieService: MovieService

    @Before
    fun setUp() {
        mockWebServer.start()
        mockWebServer.dispatcher = setUpMockWebServerDispatcher()
        setUpMovieRetrofitService()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Assert get movies remote response structure match JSON Server response`() = runBlocking {
        // This shouldn't have to throw an error if the MovieResponse
        // is well mapped with the server response mocked in [setUpMockWebServerDispatcher]
        val movies = movieService.getTopRatedMovies(
            language = "en-US",
            page = 1
        )

        assertEquals(
            "Movies size does not match the one provided in resources.",
            MoviesData.provideRemoteMoviesFromAssets().size,
            movies.results.size
        )
    }

    private fun setUpMovieRetrofitService() {
        movieService = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(MovieService::class.java)
    }

    private fun setUpMockWebServerDispatcher(): Dispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            println("BASE_URL${request.path}")
            return when (request.path) {
                "/movie/top_rated?language=en-US&page=1" -> {
                    MockResponse()
                        .setResponseCode(200)
                        .setBody(FileReaderUtil.kotlinReadFileWithNewLineFromResources("movies.json"))
                }
                else -> MockResponse().setResponseCode(404)
            }
        }
    }
}