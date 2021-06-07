package com.christopher_elias.features.actors

import com.christopher_elias.features.actors.data.ActorsData
import com.christopher_elias.features.actors.data_source.remote.retrofit.ActorsService
import com.christopher_elias.test_shared.file.FileReaderUtil
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/*
 * Created by Christopher Elias on 3/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class ActorsRetrofitServiceUnitTest {

    //This will test if our data classes are well mapped with the expected response.
    private val mockWebServer = MockWebServer()
    private lateinit var actorsService: ActorsService

    @Before
    fun setUp() {
        mockWebServer.start()
        mockWebServer.dispatcher = setUpMockWebServerDispatcher()
        setUpActorsRetrofitService()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Assert get actors remote response structure match JSON Server response`() = runBlocking {
        // This shouldn't have to throw an error if the MovieResponse
        // is well mapped with the server response mocked in [setUpMockWebServerDispatcher]
        val actors = actorsService.getActors(
            language = "en-US",
            page = 1
        )

        Assert.assertEquals(
            "Actors size does not match the one provided in resources.",
            ActorsData.provideRemoteActorsFromAssets().size,
            actors.results.size
        )
    }

    private fun setUpActorsRetrofitService() {
        actorsService = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(ActorsService::class.java)
    }

    private fun setUpMockWebServerDispatcher(): Dispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            println("BASE_URL${request.path}")
            return when (request.path) {
                "/person/popular?language=en-US&page=1" -> {
                    MockResponse()
                        .setResponseCode(200)
                        .setBody(FileReaderUtil.kotlinReadFileWithNewLineFromResources("actors.json"))
                }
                else -> MockResponse().setResponseCode(404)
            }
        }
    }
}