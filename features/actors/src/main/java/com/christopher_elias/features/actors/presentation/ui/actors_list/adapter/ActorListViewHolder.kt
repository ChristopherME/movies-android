package com.christopher_elias.features.actors.presentation.ui.actors_list.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.christopher_elias.features.actors.R
import com.christopher_elias.features.actors.databinding.ItemActorBinding
import com.christopher_elias.features.actors.presentation.model.ActorUi

/*
 * Created by Christopher Elias on 4/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

internal class ActorListViewHolder(
    val binding: ItemActorBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(actor: ActorUi) {
        binding.tvActorName.text = actor.name
        binding.tvActorWorkOn.text = actor.moviesNames
        binding.ivActorProfileImage.load(actor.profilePath) {
            fallback(R.drawable.ic_failure)
            crossfade(durationMillis = 2000)
            transformations(CircleCropTransformation())
        }
    }
}