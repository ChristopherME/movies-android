package com.christopher_elias.features.actors.mapper

import com.christopher_elias.features.actors.data_source.models.ActorsResponse
import com.christopher_elias.features.actors.domain.models.Actor
import com.christopher_elias.features.actors.presentation.model.ActorUi

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

interface ActorsMapper {

    suspend fun mapRemoteActorsToDomain(remoteActors: List<ActorsResponse>): List<Actor>

    suspend fun mapRemoteActorToDomain(remoteActor: ActorsResponse): Actor

    suspend fun mapDomainActorsToUi(domainActors: List<Actor>): List<ActorUi>

    suspend fun mapDomainActorToUi(domainActor: Actor): ActorUi
}