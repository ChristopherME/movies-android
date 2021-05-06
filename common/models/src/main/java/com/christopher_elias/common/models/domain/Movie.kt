package com.christopher_elias.common.models.domain

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

data class Movie(
    val id: Int,
    val isAdultOnly: Boolean,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int,
    val image: String,
    val backdropImage: String,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val originalLanguage: String
)