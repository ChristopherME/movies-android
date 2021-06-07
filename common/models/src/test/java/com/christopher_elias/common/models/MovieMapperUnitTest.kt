package com.christopher_elias.common.models

import com.christopher_elias.common.models.data.MovieResponse
import com.christopher_elias.common.models.domain.Movie
import com.christopher_elias.common.models.mapper.MovieMapper
import com.christopher_elias.common.models.mapper.MovieMapperImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@ExperimentalCoroutinesApi
class MovieMapperUnitTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val mapper: MovieMapper = MovieMapperImpl(defaultDispatcher = testCoroutineDispatcher)

    @Test
    fun `assert map REMOTE movies to DOMAIN movies is passing the right data`() = runBlockingTest {
        // Assume we get an structure like this. With the image null but with a backDropImage working.
        val remoteMovie = MovieResponse(
            id = 1,
            isAdultOnly = true,
            popularity = 9.5,
            voteAverage = 10.5,
            voteCount = 100,
            image = null,
            backdropImage = "",
            title = "Movie",
            overview = "From remote service",
            releaseDate = "12/12/2012",
            originalTitle = "Original",
            originalLanguage = "US"
        )
        val remoteMovies = listOf(remoteMovie)

        val domainMovie = mapper.mapRemoteMoviesListToDomain(remoteMovies)
            .first()

        assertEquals(
            "Remote movie Id is not the same as the Domain movie id",
            remoteMovie.id,
            domainMovie.id
        )
        assertEquals(
            "Remote movie isAdultOnly field is not the same as the Domain movie",
            remoteMovie.isAdultOnly,
            domainMovie.isAdultOnly
        )
        assertEquals(
            "Remote movie popularity field is not the same as the Domain movie",
            remoteMovie.popularity,
            domainMovie.popularity,
            0.001
        )
        assertEquals(
            "Remote movie voteAverage field is not the same as the Domain movie",
            remoteMovie.voteAverage,
            domainMovie.voteAverage,
            0.001
        )
        assertEquals(
            "Remote movie voteCount field is not the same as the Domain movie",
            remoteMovie.voteCount,
            domainMovie.voteCount
        )
        assertNull(
            "Remote movie image field is null",
            remoteMovie.image
        )
        assertEquals(
            "Remote movie backdropImage field is not the same as the Domain movie",
            remoteMovie.backdropImage,
            domainMovie.image
        )
        assertEquals(
            "Remote movie title field is not the same as the Domain movie",
            remoteMovie.title,
            domainMovie.title
        )
        assertEquals(
            "Remote movie overview field is not the same as the Domain movie",
            remoteMovie.overview,
            domainMovie.overview
        )
        assertEquals(
            "Remote movie releaseDate field is not the same as the Domain movie",
            remoteMovie.releaseDate,
            domainMovie.releaseDate
        )
        assertEquals(
            "Remote movie originalTitle field is not the same as the Domain movie",
            remoteMovie.title,
            domainMovie.title
        )
        assertEquals(
            "Remote movie originalLanguage field is not the same as the Domain movie",
            remoteMovie.originalLanguage,
            domainMovie.originalLanguage
        )
        
    }
    
    @Test
    fun `assert map DOMAIN movies to PRESENTATION UI movies is passing the right data`() = runBlockingTest {
        val domainMovie = Movie(
            id = 1,
            isAdultOnly = true,
            popularity = 9.5,
            voteAverage = 10.5,
            voteCount = 100,
            image = "someImageUrl.com",
            title = "Movie",
            overview = "From remote service",
            releaseDate = "12/12/2012",
            originalLanguage = "US"
        )
        val domainMovies = listOf(domainMovie)

        val uiMovie = mapper.mapDomainMoviesListToUi(domainMovies)
            .first()

        assertEquals(
            "Domain movie Id is not the same as The UI movie id",
            domainMovie.id,
            uiMovie.id
        )
        assertEquals(
            "Domain movie isAdultOnly field is not the same as The UI movie",
            domainMovie.isAdultOnly,
            uiMovie.isAdultOnly
        )
        assertEquals(
            "Domain movie popularity field is not the same as The UI movie",
            domainMovie.popularity,
            uiMovie.popularity,
            0.001
        )
        assertEquals(
            "Domain movie voteAverage field is not the same as The UI movie",
            domainMovie.voteAverage,
            uiMovie.voteAverage,
            0.001
        )
        assertEquals(
            "Domain movie voteCount field is not the same as The UI movie",
            domainMovie.voteCount,
            uiMovie.voteCount
        )
        assertEquals(
            "Domain movie image field is not the same as The UI movie",
            domainMovie.image,
            uiMovie.image
        )
        assertEquals(
            "Domain movie title field is not the same as The UI movie",
            domainMovie.title,
            uiMovie.title
        )
        assertEquals(
            "Domain movie overview field is not the same as The UI movie",
            domainMovie.overview,
            uiMovie.overview
        )
        assertEquals(
            "Domain movie releaseDate field is not the same as The UI movie",
            domainMovie.releaseDate,
            uiMovie.releaseDate
        )
        assertEquals(
            "Domain movie title field is not the same as The UI movie",
            domainMovie.title,
            uiMovie.title
        )
        assertEquals(
            "Domain movie originalLanguage field is not the same as The UI movie",
            domainMovie.originalLanguage,
            uiMovie.originalLanguage
        )
    }
}