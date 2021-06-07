package com.christopher_elias.features.actors.domain.models

import com.christopher_elias.common.models.domain.Movie

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

data class Actor(
    val id: Int,
    val name: String,
    val popularity: Double,
    val profilePath: String?,
    val knownFor: List<Movie>
)