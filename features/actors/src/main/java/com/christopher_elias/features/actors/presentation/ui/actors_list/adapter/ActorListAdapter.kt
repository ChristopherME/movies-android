package com.christopher_elias.features.actors.presentation.ui.actors_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.christopher_elias.features.actors.databinding.ItemActorBinding
import com.christopher_elias.features.actors.presentation.model.ActorUi

/*
 * Created by Christopher Elias on 4/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

internal class ActorListAdapter(
    val listener: (item: ActorUi) -> Unit
) : ListAdapter<ActorUi, ActorListViewHolder>(ActorDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorListViewHolder {
        val holder = ActorListViewHolder(
            ItemActorBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        holder.binding.root.setOnClickListener {
            listener(getItem(holder.adapterPosition))
        }
        return holder
    }

    override fun onBindViewHolder(holder: ActorListViewHolder, position: Int) {
        holder.bind(actor = getItem(position))
    }
}