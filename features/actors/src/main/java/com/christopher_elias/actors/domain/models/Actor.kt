package com.christopher_elias.actors.domain.models

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.elias@loop-ideas.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

data class Actor(
    val id: Int,
    val popularity: Double,
    val profilePath: String,
    val knownFor: List<MovieResume>
)

data class MovieResume(
    val id: Int,
    val originalTitle: String,
    val posterPath: String
)