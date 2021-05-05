package com.christopher_elias.actors

import com.christopher_elias.actors.data_source.models.ActorsResponse
import com.christopher_elias.actors.data_source.models.MovieResumeResponse
import com.christopher_elias.actors.domain.models.Actor
import com.christopher_elias.actors.domain.models.MovieResume
import com.christopher_elias.actors.mapper.ActorsMapper
import com.christopher_elias.actors.mapper.ActorsMapperImpl
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
 * Loop Ideas
 * Lima, Peru.
 */

@ExperimentalCoroutinesApi
class ActorsMapperUnitTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val mockResourceProvider: ResourceProvider = MockResourceProviderImpl()
    private val mapper: ActorsMapper = ActorsMapperImpl(
        defaultDispatcher = testCoroutineDispatcher,
        resourceProvider = mockResourceProvider
    )

    @Test
    fun `assert map REMOTE actors to DOMAIN actors is passing the right data`() = runBlockingTest {
        // remote fake data
        val remoteActorResumedMovie = MovieResumeResponse(
            id = 1,
            originalTitle = "title",
            posterPath = "path"
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
            remoteActor.profilePath,
            domainActor.profilePath
        )

        assertEquals(
            "Remote resumed movie id is not the same as the Domain resumed movie id",
            remoteActorResumedMovie.id,
            domainActorResumedMovie.id
        )

        assertEquals(
            "Remote resumed movie originalTitle is not the same as the Domain resumed movie originalTitle",
            remoteActorResumedMovie.originalTitle,
            domainActorResumedMovie.originalTitle
        )

        assertEquals(
            "Remote resumed movie posterPath is not the same as the Domain resumed movie posterPath",
            remoteActorResumedMovie.posterPath,
            domainActorResumedMovie.posterPath
        )

    }

    @Test
    fun `assert map DOMAIN actors to PRESENTATION UI actors is passing the right data`() =
        runBlockingTest {
            // domain fake data
            val domainActorResumedMovie = MovieResume(
                id = 1,
                originalTitle = "title",
                posterPath = "path"
            )
            val domainActorResumedMovie2 = MovieResume(
                id = 1,
                originalTitle = "title 2",
                posterPath = "path2"
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
                "Domain resumed movie originalTitle is not the same as the UI resumed movie originalTitle",
                domainActorResumedMovie.originalTitle,
                uiActorResumedMovie.originalTitle
            )

            assertEquals(
                "Domain resumed movie posterPath is not the same as the UI resumed movie posterPath",
                domainActorResumedMovie.posterPath,
                uiActorResumedMovie.posterPath
            )
        }
}