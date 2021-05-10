package com.christopher_elias.features.movies.presentation.ui.movies_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import com.christopher_elias.common.models.presentation.MovieUi
import com.christopher_elias.common.ui_components.MoviePosterViewHolder
import com.christopher_elias.common.ui_components.databinding.ItemMoviePosterBinding
import com.christopher_elias.features.movies.presentation.ui.movies_list.MovieListFragmentDirections

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

class MovieListAdapter : PagingDataAdapter<MovieUi, MoviePosterViewHolder>(MovieDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePosterViewHolder {
        val holder = MoviePosterViewHolder(
            ItemMoviePosterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        holder.binding.root.setOnClickListener { view ->
            getItem(holder.adapterPosition)?.let { movieUi ->
                view.findNavController().navigate(
                    MovieListFragmentDirections.actionGoToSheetDetail(
                        movie = movieUi
                    )
                )
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: MoviePosterViewHolder, position: Int) {
        holder.bind(getItem(position)?.image)
    }
}