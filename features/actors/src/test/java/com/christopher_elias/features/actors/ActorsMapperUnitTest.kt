package com.christopher_elias.features.actors

import com.christopher_elias.common.models.data.MovieResponse
import com.christopher_elias.common.models.domain.Movie
import com.christopher_elias.common.models.mapper.MovieMapper
import com.christopher_elias.common.models.mapper.MovieMapperImpl
import com.christopher_elias.features.actors.data_source.models.ActorsResponse
import com.christopher_elias.features.actors.domain.models.Actor
import com.christopher_elias.features.actors.mapper.ActorsMapper
import com.christopher_elias.features.actors.mapper.ActorsMapperImpl
import com.christopher_elias.features.actors.mapper.completePath
import com.christopher_elias.utils.resource_provider.ResourceProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

/*
 * Created by Christopher Elias on 3/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@ExperimentalCoroutinesApi
class ActorsMapperUnitTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val mockResourceProvider: ResourceProvider = MockResourceProviderImpl()

    private val movieMapper: MovieMapper = MovieMapperImpl(
        defaultDispatcher = testCoroutineDispatcher
    )
    private val mapper: ActorsMapper = ActorsMapperImpl(
        defaultDispatcher = testCoroutineDispatcher,
        movieMapper = movieMapper,
        resourceProvider = mockResourceProvider
    )

    @Test
    fun `assert map REMOTE actors to DOMAIN actors is passing the right data`() = runBlockingTest {
        // remote fake data
        val remoteActorResumedMovie = MovieResponse(
            id = 1,
            isAdultOnly = false,
            popularity = 10.0,
            voteAverage = 98.8,
            voteCount = 2938,
            image = "image",
            backdropImage = "backDropImage",
            title = "title",
            overview = "",
            releaseDate = "2020",
            releaseDateAlternative = null,
            originalTitle = "Title",
            originalTitleAlternative = null,
            originalLanguage = "EN"
        )
        val remoteActor = ActorsResponse(
            id = 1,
            name = "Chris",
            popularity = 10.0,
            profilePath = "url",
            knownFor = listOf(remoteActorResumedMovie)
        )
        val remoteActors = listOf(remoteActor)

        // domain actors
        val domainActor = mapper.mapRemoteActorsToDomain(remoteActors)
            .first()

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
            "Remote resumed movie originalTitle is not the same as the Domain resumed movie originalTitle",
            remoteActorResumedMovie.title,
            domainActorResumedMovie.title
        )

    }

    @Test
    fun `assert map DOMAIN actors to PRESENTATION UI actors is passing the right data`() =
        runBlockingTest {
            // domain fake data
            val domainActorResumedMovie = Movie(
                id = 1,
                isAdultOnly = false,
                popularity = 10.0,
                voteAverage = 98.8,
                voteCount = 2938,
                image = "image",
                title = "title",
                overview = "overview",
                releaseDate = "2020",
                originalLanguage = "EN"
            )
            val domainActorResumedMovie2 = Movie(
                id = 2,
                isAdultOnly = false,
                popularity = 10.0,
                voteAverage = 98.8,
                voteCount = 2938,
                image = "image",
                title = "title 2",
                overview = "overview",
                releaseDate = "2019",
                originalLanguage = "EN"
            )
            val domainActor = Actor(
                id = 1,
                name = "Chris",
                popularity = 10.0,
                profilePath = "url",
                knownFor = listOf(domainActorResumedMovie, domainActorResumedMovie2)
            )

            val domainActors = listOf(domainActor)

            //  UI 
            val actorUi = mapper.mapDomainActorsToUi(domainActors)
                .first()

            val uiActorResumedMovie = actorUi.knownFor.first()


            assertEquals(
                "Domain actor Id is not the same as the UI actor id",
                domainActor.id,
                actorUi.id
            )

            assertEquals(
                "Domain actor name is not the same as the UI actor name",
                domainActor.name,
                actorUi.name
            )

            assertEquals(
                "Domain actor popularity is not the same as the UI actor popularity",
                domainActor.popularity,
                actorUi.popularity,
                0.01
            )

            assertEquals(
                "Domain actor profilePath is not the same as the UI actor profilePath",
                domainActor.profilePath,
                actorUi.profilePath
            )

            assertEquals(
                "The UI actor movie names are not being mapped as expected",
                "title, title 2",
                actorUi.moviesNames
            )

            assertEquals(
                "Domain resumed movie id is not the same as the UI resumed movie id",
                domainActorResumedMovie.id,
                uiActorResumedMovie.id
            )

            assertEquals(
                "Domain resumed movie title is not the same as the UI resumed movie originalTitle",
                domainActorResumedMovie.title,
                uiActorResumedMovie.title
            )

            assertEquals(
                "Domain resumed movie posterPath is not the same as the UI resumed movie posterPath",
                domainActorResumedMovie.image,
                uiActorResumedMovie.image
            )
        }
}