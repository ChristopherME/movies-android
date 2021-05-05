package com.christopher_elias.features.actors.presentation.ui.actors_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import com.christopher_elias.features.actors.databinding.ItemActorBinding
import com.christopher_elias.features.actors.presentation.model.ActorUi
import com.christopher_elias.features.actors.presentation.ui.actors_list.ActorsListFragmentDirections

/*
 * Created by Christopher Elias on 4/05/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

internal class ActorListAdapter : ListAdapter<ActorUi, ActorListViewHolder>(ActorDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorListViewHolder {
        val holder = ActorListViewHolder(
            ItemActorBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        holder.binding.root.setOnClickListener {
            it.findNavController().navigate(
                ActorsListFragmentDirections.actionGoToActorDetail(
                    getItem(holder.adapterPosition)
                )
            )
        }
        return holder
    }

    override fun onBindViewHolder(holder: ActorListViewHolder, position: Int) {
        holder.bind(actor = getItem(position))
    }
}