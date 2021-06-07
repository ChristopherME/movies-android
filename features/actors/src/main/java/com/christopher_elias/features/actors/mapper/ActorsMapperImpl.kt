package com.christopher_elias.features.actors.mapper

import com.christopher_elias.common.models.mapper.MovieMapper
import com.christopher_elias.features.actors.R
import com.christopher_elias.features.actors.data_source.models.ActorsResponse
import com.christopher_elias.features.actors.domain.models.Actor
import com.christopher_elias.features.actors.presentation.model.ActorUi
import com.christopher_elias.utils.resource_provider.ResourceProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

internal class ActorsMapperImpl(
    private val defaultDispatcher: CoroutineDispatcher,
    private val movieMapper: MovieMapper,
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
            profilePath = remoteActor.profilePath.completePath(),
            knownFor = movieMapper.mapRemoteMoviesListToDomain(remoteActor.knownFor)
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
                domainActor.knownFor.joinToString { movieResume -> movieResume.title }
            ),
            knownFor = movieMapper.mapDomainMoviesListToUi(domainActor.knownFor)
        )
    }
}

fun String?.completePath(): String? {
    if (this == null) return null
    return "https://image.tmdb.org/t/p/w185/$this"
}