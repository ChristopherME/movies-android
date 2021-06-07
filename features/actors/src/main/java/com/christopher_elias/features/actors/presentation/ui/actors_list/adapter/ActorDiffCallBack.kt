package com.christopher_elias.features.actors.presentation.ui.actors_list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.christopher_elias.features.actors.presentation.model.ActorUi

/*
 * Created by Christopher Elias on 4/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

internal class ActorDiffCallBack : DiffUtil.ItemCallback<ActorUi>() {
    override fun areItemsTheSame(oldItem: ActorUi, newItem: ActorUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ActorUi, newItem: ActorUi): Boolean {
        return oldItem == newItem
    }
}