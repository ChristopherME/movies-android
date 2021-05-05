package com.christopher_elias.features.actors.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

@Parcelize
data class ActorUi(
    val id: Int,
    val name: String,
    val popularity: Double,
    val profilePath: String,
    val moviesNames: String,
    val knownFor: List<MovieResumeUi>
) : Parcelable

@Parcelize
data class MovieResumeUi(
    val id: Int,
    val originalTitle: String,
    val posterPath: String
) : Parcelable