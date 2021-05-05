package com.christopher_elias.actors.mapper

import com.christopher_elias.actors.R
import com.christopher_elias.actors.data_source.models.ActorsResponse
import com.christopher_elias.actors.data_source.models.MovieResumeResponse
import com.christopher_elias.actors.domain.models.Actor
import com.christopher_elias.actors.domain.models.MovieResume
import com.christopher_elias.actors.presentation.model.ActorUi
import com.christopher_elias.actors.presentation.model.MovieResumeUi
import com.christopher_elias.utils.resource_provider.ResourceProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

internal class ActorsMapperImpl(
    private val defaultDispatcher: CoroutineDispatcher,
    private val resourceProvider: ResourceProvider
) : ActorsMapper {

    override suspend fun mapRemoteActorsToDomain(
        remoteActors: List<ActorsResponse>
    ): List<Actor> {
        return withContext(defaultDispatcher) {
            remoteActors.map { mapRemoteActorToDomain(it) }
        }
    }

    override suspend fun mapRemoteActorToDomain(
        remoteActor: ActorsResponse
    ): Actor {
        return Actor(
            id = remoteActor.id,
            name = remoteActor.name,
            popularity = remoteActor.popularity,
            profilePath = remoteActor.profilePath,
            knownFor = mapRemoteMoviesResumedToDomain(remoteActor.knownFor)
        )
    }

    override suspend fun mapRemoteMoviesResumedToDomain(
        remoteMovies: List<MovieResumeResponse>
    ): List<MovieResume> {
        return withContext(defaultDispatcher) {
            remoteMovies.map { mapRemoteMovieResumedToDomain(it) }
        }
    }

    override suspend fun mapRemoteMovieResumedToDomain(
        remoteMovie: MovieResumeResponse
    ): MovieResume {
        return MovieResume(
            id = remoteMovie.id,
            originalTitle = remoteMovie.originalTitle ?: "",
            posterPath = remoteMovie.posterPath
        )
    }

    override suspend fun mapDomainActorsToUi(
        domainActors: List<Actor>
    ): List<ActorUi> {
        return withContext(defaultDispatcher) {
            domainActors.map { mapDomainActorToUi(it) }
        }
    }

    override suspend fun mapDomainActorToUi(
        domainActor: Actor
    ): ActorUi {
        return ActorUi(
            id = domainActor.id,
            name = domainActor.name,
            popularity = domainActor.popularity,
            profilePath = domainActor.profilePath,
            moviesNames = resourceProvider.getString(
                R.string.feature_actors_tv_work_on,
                domainActor.knownFor.joinToString { movieResume -> movieResume.originalTitle }
            ),
            knownFor = mapDomainMoviesResumedToUi(domainActor.knownFor)
        )
    }

    override suspend fun mapDomainMoviesResumedToUi(
        domainMovies: List<MovieResume>
    ): List<MovieResumeUi> {
        return withContext(defaultDispatcher) {
            domainMovies.map { mapDomainMovieResumedToUi(it) }
        }
    }

    override suspend fun mapDomainMovieResumedToUi(
        domainMovie: MovieResume
    ): MovieResumeUi {
        return MovieResumeUi(
            id = domainMovie.id,
            originalTitle = domainMovie.originalTitle,
            posterPath = domainMovie.posterPath
        )
    }
}