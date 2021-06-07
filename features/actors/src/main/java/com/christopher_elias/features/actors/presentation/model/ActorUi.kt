package com.christopher_elias.features.actors.presentation.model

import android.os.Parcelable
import com.christopher_elias.common.models.presentation.MovieUi
import kotlinx.parcelize.Parcelize

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@Parcelize
data class ActorUi(
    val id: Int,
    val name: String,
    val popularity: Double,
    val profilePath: String?,
    val moviesNames: String,
    val knownFor: List<MovieUi>
) : Parcelable