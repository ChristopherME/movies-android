package com.christopher_elias.features.movies.data_source.model

import com.squareup.moshi.Json

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

data class MovieResponse(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "adult") val isAdultOnly: Boolean,
    @field:Json(name = "popularity") val popularity: Double,
    @field:Json(name = "vote_average") val voteAverage: Double,
    @field:Json(name = "vote_count") val voteCount: Int,
    @field:Json(name = "poster_path") val image: String,
    @field:Json(name = "backdrop_path") val backdropImage: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "overview") val overview: String,
    @field:Json(name = "release_date") val releaseDate: String,
    @field:Json(name = "original_title") val originalTitle: String,
    @field:Json(name = "original_language") val originalLanguage: String
)