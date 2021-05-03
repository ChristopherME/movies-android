package com.christopher_elias.actors.presentation.model

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.elias@loop-ideas.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

data class ActorUi(
    val id: Int,
    val popularity: Double,
    val profilePath: String,
    val knownFor: List<MovieResumeUi>
)

data class MovieResumeUi(
    val id: Int,
    val originalTitle: String,
    val posterPath: String
)