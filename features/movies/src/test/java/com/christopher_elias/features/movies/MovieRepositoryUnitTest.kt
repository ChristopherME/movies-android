package com.christopher_elias.features.movies

import com.christopher_elias.features.movies.data.MoviesData
import com.christopher_elias.features.movies.data.data_source.MoviesRemoteDataSource
import com.christopher_elias.features.movies.data.repository.MoviesRepositoryImpl
import com.christopher_elias.common.models.data.MovieResponse
import com.christopher_elias.features.movies.data_source.remote.MoviesRemoteDataSourceImpl
import com.christopher_elias.features.movies.data_source.remote.retrofit_service.MovieService
import com.christopher_elias.features.movies.domain.MoviesRepository
import com.christopher_elias.common.models.mapper.MovieMapper
import com.christopher_elias.common.models.mapper.MovieMapperImpl
import com.christopher_elias.network.middleware.provider.MiddlewareProvider
import com.christopher_elias.network.models.base.ResponseItems
import com.christopher_elias.network.models.exception.NetworkMiddlewareFailure
import com.christopher_elias.network.models.exception.ServiceBodyFailure
import com.christopher_elias.test_shared.either.getDataWhenResultIsFailureOrThrowException
import com.christopher_elias.test_shared.either.getDataWhenResultIsSuccessOrThrowException
import com.christopher_elias.test_shared.middleware.DefaultTestNetworkMiddleware
import com.christopher_elias.test_shared.network.DefaultRemoteConfig
import io.mockk.*
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
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@ExperimentalCoroutinesApi
class MovieRepositoryUnitTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val remoteErrorAdapter = DefaultRemoteConfig.provideRemoteErrorAdapter()

    private val movieService = mockk<MovieService>()
    private val middlewareProvider = mockk<MiddlewareProvider>()

    private val remoteDataSource: MoviesRemoteDataSource = MoviesRemoteDataSourceImpl(
        middlewareProvider = middlewareProvider,
        ioDispatcher = testDispatcher,
        adapter = remoteErrorAdapter,
        movieService = movieService
    )

    private val mapper: MovieMapper = MovieMapperImpl(
        defaultDispatcher = testDispatcher
    )

    private val repository: MoviesRepository = MoviesRepositoryImpl(
        remoteDataSource = remoteDataSource,
        mapper = mapper
    )

    @Test
    fun `Assert repository return movies when remote service works as expected`() {
        val remoteMovies: List<MovieResponse> = MoviesData.provideRemoteMoviesFromAssets()

        every { middlewareProvider.getAll() } returns listOf(
            DefaultTestNetworkMiddleware(
                isMiddlewareValid = true
            )
        )

        coEvery {
            movieService.getTopRatedMovies(language = any(), page = any())
        } returns ResponseItems(remoteMovies)

        runBlockingTest {
            repository.getMovies().getDataWhenResultIsSuccessOrThrowException { domainMovies ->
                assertEquals(
                    "Remote movies size does not match the domain movies size",
                    remoteMovies.size,
                    domainMovies.size
                )
                val firstRemoteMovie = remoteMovies.first()
                val firstDomainMovie = domainMovies.first()

                assertEquals(
                    "Remote movie Id is not the same as the Domain movie id",
                    firstRemoteMovie.id,
                    firstDomainMovie.id
                )
                assertEquals(
                    "Remote movie isAdultOnly field is not the same as the Domain movie",
                    firstRemoteMovie.isAdultOnly,
                    firstDomainMovie.isAdultOnly
                )
                assertEquals(
                    "Remote movie popularity field is not the same as the Domain movie",
                    firstRemoteMovie.popularity,
                    firstDomainMovie.popularity,
                    0.001
                )
                assertEquals(
                    "Remote movie voteAverage field is not the same as the Domain movie",
                    firstRemoteMovie.voteAverage,
                    firstDomainMovie.voteAverage,
                    0.001
                )
                assertEquals(
                    "Remote movie voteCount field is not the same as the Domain movie",
                    firstRemoteMovie.voteCount,
                    firstDomainMovie.voteCount
                )
                assertEquals(
                    "Remote movie final Image field is not the same as the Domain movie",
                    firstRemoteMovie.image ?: firstRemoteMovie.backdropImage ?: "",
                    firstDomainMovie.image
                )
                assertEquals(
                    "Remote movie title field is not the same as the Domain movie",
                    firstRemoteMovie.title,
                    firstDomainMovie.title
                )
                assertEquals(
                    "Remote movie overview field is not the same as the Domain movie",
                    firstRemoteMovie.overview,
                    firstDomainMovie.overview
                )
                assertEquals(
                    "Remote movie releaseDate field is not the same as the Domain movie",
                    firstRemoteMovie.releaseDate,
                    firstDomainMovie.releaseDate
                )
                assertEquals(
                    "Remote movie title field is not the same as the Domain movie",
                    firstRemoteMovie.title,
                    firstDomainMovie.title
                )
                assertEquals(
                    "Remote movie originalLanguage field is not the same as the Domain movie",
                    firstRemoteMovie.originalLanguage,
                    firstDomainMovie.originalLanguage
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
            repository.getMovies().getDataWhenResultIsFailureOrThrowException { failure ->
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

            // Verify the movie service was not called this has returned a failure.
            coVerify(exactly = 0) { movieService.getTopRatedMovies(any(), any()) }
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
            movieService.getTopRatedMovies(any(), any())
        } throws HttpException(Response.error<Any>(400, errorBody))

        runBlockingTest {
            repository.getMovies()
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