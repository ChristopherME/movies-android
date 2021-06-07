package com.christopher_elias.features.actors.data.repository

import com.christopher_elias.features.actors.data.data_source.ActorsRemoteDataSource
import com.christopher_elias.features.actors.domain.models.Actor
import com.christopher_elias.features.actors.domain.repository.ActorsRepository
import com.christopher_elias.features.actors.mapper.ActorsMapper
import com.christopher_elias.functional_programming.Either
import com.christopher_elias.functional_programming.Failure

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

internal class ActorsRepositoryImpl(
    private val remoteDataSource: ActorsRemoteDataSource,
    private val mapper: ActorsMapper
) : ActorsRepository {

    override suspend fun getActors(): Either<Failure, List<Actor>> {
        return remoteDataSource.getActors()
            .coMapSuccess { remoteItems ->
                mapper.mapRemoteActorsToDomain(remoteItems)
            }
    }
}