package com.christopher_elias.actors.data_source.models

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.elias@loop-ideas.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

data class ActorsResponse(
    val id: Int,
    val popularity: Double,
    val profilePath: String,
    val knownFor: List<MovieResumeResponse>
)

data class MovieResumeResponse(
    val id: Int,
    val originalTitle: String,
    val posterPath: String
)