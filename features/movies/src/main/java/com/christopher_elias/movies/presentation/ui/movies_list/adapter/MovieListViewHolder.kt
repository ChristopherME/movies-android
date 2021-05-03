package com.christopher_elias.movies.presentation.ui.movies_list.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.christopher_elias.movies.databinding.ItemMovieBinding

/*
 * Created by Christopher Elias on 3/05/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

/**
 * @see [https://developers.themoviedb.org/3/configuration/get-api-configuration]
 */
class MovieListViewHolder(
    val binding: ItemMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(path: String) {
        binding.ivMoviePoster.load("https://image.tmdb.org/t/p/w500/$path") {
            crossfade(durationMillis = 2000)
            transformations(RoundedCornersTransformation(25f))
        }
    }
}