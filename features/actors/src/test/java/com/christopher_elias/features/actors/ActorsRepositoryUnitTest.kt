package com.christopher_elias.features.actors

import com.christopher_elias.common.models.mapper.MovieMapper
import com.christopher_elias.common.models.mapper.MovieMapperImpl
import com.christopher_elias.features.actors.data.ActorsData
import com.christopher_elias.features.actors.data.data_source.ActorsRemoteDataSource
import com.christopher_elias.features.actors.data.repository.ActorsRepositoryImpl
import com.christopher_elias.features.actors.data_source.models.ActorsResponse
import com.christopher_elias.features.actors.data_source.remote.ActorsRemoteDataSourceImpl
import com.christopher_elias.features.actors.data_source.remote.retrofit.ActorsService
import com.christopher_elias.features.actors.domain.repository.ActorsRepository
import com.christopher_elias.features.actors.mapper.ActorsMapper
import com.christopher_elias.features.actors.mapper.ActorsMapperImpl
import com.christopher_elias.features.actors.mapper.completePath
import com.christopher_elias.network.middleware.provider.MiddlewareProvider
import com.christopher_elias.network.models.base.ResponseItems
import com.christopher_elias.network.models.exception.NetworkMiddlewareFailure
import com.christopher_elias.network.models.exception.ServiceBodyFailure
import com.christopher_elias.test_shared.either.getDataWhenResultIsFailureOrThrowException
import com.christopher_elias.test_shared.either.getDataWhenResultIsSuccessOrThrowException
import com.christopher_elias.test_shared.middleware.DefaultTestNetworkMiddleware
import com.christopher_elias.test_shared.network.DefaultRemoteConfig
import com.christopher_elias.utils.resource_provider.ResourceProvider
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

/*
 * Created by Christopher Elias on 3/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@ExperimentalCoroutinesApi
class ActorsRepositoryUnitTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val remoteErrorAdapter = DefaultRemoteConfig.provideRemoteErrorAdapter()

    private val actorsService = mockk<ActorsService>()
    private val middlewareProvider = mockk<MiddlewareProvider>()

    private val remoteDataSource: ActorsRemoteDataSource = ActorsRemoteDataSourceImpl(
        middlewareProvider = middlewareProvider,
        ioDispatcher = testDispatcher,
        errorAdapter = remoteErrorAdapter,
        actorsService = actorsService
    )

    private val mockResourceProvider: ResourceProvider = MockResourceProviderImpl()

    private val movieMapper: MovieMapper = MovieMapperImpl(
        defaultDispatcher = testDispatcher
    )

    private val mapper: ActorsMapper = ActorsMapperImpl(
        defaultDispatcher = testDispatcher,
        movieMapper = movieMapper,
        resourceProvider = mockResourceProvider
    )

    private val repository: ActorsRepository = ActorsRepositoryImpl(
        remoteDataSource = remoteDataSource,
        mapper = mapper
    )

    @Test
    fun `Assert repository return actors when remote service works as expected`() {
        val remoteActors: List<ActorsResponse> = ActorsData.provideRemoteActorsFromAssets()

        every { middlewareProvider.getAll() } returns listOf(
            DefaultTestNetworkMiddleware(
                isMiddlewareValid = true
            )
        )

        coEvery {
            actorsService.getActors(language = any(), page = any())
        } returns ResponseItems(remoteActors)

        runBlockingTest {
            repository.getActors().getDataWhenResultIsSuccessOrThrowException { domainActors ->
                assertEquals(
                    "Remote actors size does not match the domain actors size",
                    remoteActors.size,
                    domainActors.size
                )
                val remoteActor = remoteActors.first()
                val remoteActorResumedMovie = remoteActor.knownFor.first()

                val domainActor = domainActors.first()
                val domainActorResumedMovie = domainActor.knownFor.first()

                assertEquals(
                    "Remote actor Id is not the same as the Domain actor id",
                    remoteActor.id,
                    domainActor.id
                )

                assertEquals(
                    "Remote actor name is not the same as the Domain actor name",
                    remoteActor.name,
                    domainActor.name
                )

                assertEquals(
                    "Remote actor popularity is not the same as the Domain actor popularity",
                    remoteActor.popularity,
                    domainActor.popularity,
                    0.01
                )

                assertEquals(
                    "Remote actor profilePath is not the same as the Domain actor profilePath",
                    remoteActor.profilePath.completePath(),
                    domainActor.profilePath
                )

                assertEquals(
                    "Remote resumed movie id is not the same as the Domain resumed movie id",
                    remoteActorResumedMovie.id,
                    domainActorResumedMovie.id
                )

                assertEquals(
                    "Remote resumed movie title is not the same as the Domain resumed movie originalTitle",
                    remoteActorResumedMovie.title,
                    domainActorResumedMovie.title
                )

                assertEquals(
                    "Remote resumed movie posterPath is not the same as the Domain resumed movie posterPath",
                    remoteActorResumedMovie.image,
                    domainActorResumedMovie.image
                )
            }
        }

    }

    @Test
    fun `Assert repository return network middleware failure message properly`() {
        // If the Network middleware condition it should return some message
        // and SHOULDN'T invoke the retrofit service call.

        every { middlewareProvider.getAll() } returns listOf(
            DefaultTestNetworkMiddleware(
                isMiddlewareValid = false,
                failureMessage = "No network detected"
            )
        )

        runBlockingTest {
            repository.getActors().getDataWhenResultIsFailureOrThrowException { failure ->
                assertTrue(
                    "Failure returned by repository is not of NetworkMiddlewareFailure",
                    failure is NetworkMiddlewareFailure
                )

                with(failure as NetworkMiddlewareFailure) {
                    // This has to match the Middleware failure message
                    // from your remoteDataSource middleWareFailure.
                    assertEquals(
                        "No network detected",
                        middleWareExceptionMessage
                    )
                }
            }

            // Verify the actor service was not called this has returned a failure.
            coVerify(exactly = 0) { actorsService.getActors(any(), any()) }
        }
    }

    @Test
    fun `Assert repository return network service call exception properly`() {
        val errorBody = "{\"status_message\": \"Invalid Request\",\"status_code\": 400}"
            .toResponseBody("application/json".toMediaTypeOrNull())

        every { middlewareProvider.getAll() } returns listOf(
            DefaultTestNetworkMiddleware(
                isMiddlewareValid = true
            )
        )

        coEvery {
            actorsService.getActors(any(), any())
        } throws HttpException(Response.error<Any>(400, errorBody))

        runBlockingTest {
            repository.getActors()
                .getDataWhenResultIsFailureOrThrowException { failure ->
                    assertEquals(
                        ServiceBodyFailure(
                            internalCode = 400,
                            internalMessage = "Invalid Request"
                        ),
                        failure
                    )
                }
        }
    }
}