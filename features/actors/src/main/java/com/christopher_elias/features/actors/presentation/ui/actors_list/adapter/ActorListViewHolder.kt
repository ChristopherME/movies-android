package com.christopher_elias.features.actors.presentation.ui.actors_list.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.christopher_elias.features.actors.databinding.ItemActorBinding
import com.christopher_elias.features.actors.presentation.model.ActorUi

/*
 * Created by Christopher Elias on 4/05/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

internal class ActorListViewHolder(
    val binding: ItemActorBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(actor: ActorUi) {
        binding.tvActorName.text = actor.name
        binding.tvActorWorkOn.text = actor.moviesNames
        binding.ivActorProfileImage.load("https://image.tmdb.org/t/p/w185/${actor.profilePath}") {
            crossfade(durationMillis = 2000)
            transformations(CircleCropTransformation())
        }
    }
}