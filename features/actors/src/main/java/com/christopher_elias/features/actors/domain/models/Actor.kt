package com.christopher_elias.features.actors.domain.models

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

data class Actor(
    val id: Int,
    val name: String,
    val popularity: Double,
    val profilePath: String,
    val knownFor: List<MovieResume>
)

data class MovieResume(
    val id: Int,
    val originalTitle: String,
    val posterPath: String
)