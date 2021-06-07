package com.christopher_elias.common.models.data

import com.squareup.moshi.Json

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

data class MovieResponse(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "adult") val isAdultOnly: Boolean,
    @field:Json(name = "popularity") val popularity: Double,
    @field:Json(name = "vote_average") val voteAverage: Double,
    @field:Json(name = "vote_count") val voteCount: Int,
    @field:Json(name = "poster_path") val image: String? = null,
    @field:Json(name = "backdrop_path") val backdropImage: String? = null,
    @field:Json(name = "title") val title: String? = null,
    @field:Json(name = "overview") val overview: String,
    @field:Json(name = "release_date") val releaseDate: String? = null,
    @field:Json(name = "first_air_date") val releaseDateAlternative: String? = null,
    @field:Json(name = "original_title") val originalTitle: String? = null,
    @field:Json(name = "original_name") val originalTitleAlternative: String? = null,
    @field:Json(name = "original_language") val originalLanguage: String
)