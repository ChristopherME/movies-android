package com.christopher_elias.actors.mapper

import com.christopher_elias.actors.data_source.models.ActorsResponse
import com.christopher_elias.actors.domain.models.Actor
import com.christopher_elias.actors.presentation.model.ActorUi
import kotlinx.coroutines.CoroutineDispatcher

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.elias@loop-ideas.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

internal class ActorsMapperImpl(
    defaultDispatcher: CoroutineDispatcher
) : ActorsMapper {

    override suspend fun mapRemoteActorsToDomain(
        remoteActors: List<ActorsResponse>
    ): List<Actor> {
        TODO("Not yet implemented")
    }

    override suspend fun mapRemoteActorToDomain(
        remoteActor: ActorsResponse
    ): Actor {
        TODO("Not yet implemented")
    }

    override suspend fun mapDomainActorsToUi(
        domainActors: List<Actor>
    ): List<ActorUi> {
        TODO("Not yet implemented")
    }

    override suspend fun mapDomainActorToUi(
        domainActor: Actor
    ): ActorUi {
        TODO("Not yet implemented")
    }
}