package com.christopher_elias.features.actors.data_source.models

import com.christopher_elias.common.models.data.MovieResponse
import com.squareup.moshi.Json

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

data class ActorsResponse(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "popularity") val popularity: Double,
    @field:Json(name = "profile_path") val profilePath: String?,
    @field:Json(name = "known_for") val knownFor: List<MovieResponse>
)