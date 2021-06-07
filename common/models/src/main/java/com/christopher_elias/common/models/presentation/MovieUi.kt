package com.christopher_elias.common.models.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@Parcelize
data class MovieUi(
    val id: Int,
    val isAdultOnly: Boolean,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int,
    val image: String,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val originalLanguage: String
) : Parcelable