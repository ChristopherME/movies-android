package com.christopher_elias.features.actors.data_source.models

import com.squareup.moshi.Json

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

data class ActorsResponse(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "popularity") val popularity: Double,
    @field:Json(name = "profile_path") val profilePath: String,
    @field:Json(name = "known_for") val knownFor: List<MovieResumeResponse>
)

data class MovieResumeResponse(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "original_title") val originalTitle: String? = null,
    @field:Json(name = "poster_path") val posterPath: String
)