package com.christopher_elias.movies.presentation.model

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

data class MovieUi(
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
    val originalTitle: String,
    val originalLanguage: String
)