package com.christopher_elias.features.movies.presentation.ui.movies_list

import androidx.recyclerview.widget.DiffUtil
import com.christopher_elias.common.models.presentation.MovieUi

/*
 * Created by Christopher Elias on 3/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class MovieDiffCallBack : DiffUtil.ItemCallback<MovieUi>() {
    override fun areItemsTheSame(oldItem: MovieUi, newItem: MovieUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieUi, newItem: MovieUi): Boolean {
        return oldItem == newItem
    }
}