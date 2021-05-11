package com.christopher_elias.common.ui_components

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.christopher_elias.common.ui_components.databinding.ItemMoviePosterBinding

/*
 * Created by Christopher Elias on 5/05/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

/**
 * @see [https://developers.themoviedb.org/3/configuration/get-api-configuration]
 */
class MoviePosterViewHolder(
    val binding: ItemMoviePosterBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(path: String?) {
        path?.let {
            binding.ivMoviePoster.load("https://image.tmdb.org/t/p/w500/$it") {
                crossfade(durationMillis = 2000)
                transformations(RoundedCornersTransformation(12.5f))
            }
        }
    }
}