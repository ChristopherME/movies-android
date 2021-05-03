package com.christopher_elias.actors.mapper

import com.christopher_elias.actors.data_source.models.ActorsResponse
import com.christopher_elias.actors.data_source.models.MovieResumeResponse
import com.christopher_elias.actors.domain.models.Actor
import com.christopher_elias.actors.domain.models.MovieResume
import com.christopher_elias.actors.presentation.model.ActorUi
import com.christopher_elias.actors.presentation.model.MovieResumeUi

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

interface ActorsMapper {

    suspend fun mapRemoteActorsToDomain(remoteActors: List<ActorsResponse>): List<Actor>

    suspend fun mapRemoteActorToDomain(remoteActor: ActorsResponse): Actor

    suspend fun mapRemoteMoviesResumedToDomain(remoteMovies: List<MovieResumeResponse>): List<MovieResume>

    suspend fun mapRemoteMovieResumedToDomain(remoteMovie: MovieResumeResponse): MovieResume

    suspend fun mapDomainActorsToUi(domainActors: List<Actor>): List<ActorUi>

    suspend fun mapDomainActorToUi(domainActor: Actor): ActorUi

    suspend fun mapDomainMoviesResumedToUi(domainMovies: List<MovieResume>): List<MovieResumeUi>

    suspend fun mapDomainMovieResumedToUi(domainMovie: MovieResume): MovieResumeUi
}