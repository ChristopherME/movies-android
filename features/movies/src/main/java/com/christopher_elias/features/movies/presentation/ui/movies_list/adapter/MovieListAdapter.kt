package com.christopher_elias.features.movies.presentation.ui.movies_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import com.christopher_elias.features.movies.databinding.ItemMovieBinding
import com.christopher_elias.features.movies.presentation.model.MovieUi
import com.christopher_elias.features.movies.presentation.ui.movies_list.MovieListFragmentDirections

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

class MovieListAdapter : ListAdapter<MovieUi, MovieListViewHolder>(MovieDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val holder = MovieListViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        holder.binding.root.setOnClickListener {
            it.findNavController().navigate(
                MovieListFragmentDirections.actionGoToSheetDetail(
                    getItem(holder.adapterPosition)
                )
            )
        }
        return holder
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(getItem(position).image)
    }
}