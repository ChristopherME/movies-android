package com.christopher_elias.features.actors.presentation.ui.actors_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.christopher_elias.common.models.presentation.MovieUi
import com.christopher_elias.common.ui_components.MoviePosterViewHolder
import com.christopher_elias.common.ui_components.databinding.ItemMoviePosterBinding

/*
 * Created by Christopher Elias on 5/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class ActorKnownForAdapter(
    private val movies: List<MovieUi>,
    val listener: (movie: MovieUi) -> Unit
) : RecyclerView.Adapter<MoviePosterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePosterViewHolder {
        return MoviePosterViewHolder(
            ItemMoviePosterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviePosterViewHolder, position: Int) {
        holder.bind(movies[position].image)
        holder.binding.root.setOnClickListener { listener(movies[position]) }
    }

    override fun getItemCount(): Int = movies.size
}