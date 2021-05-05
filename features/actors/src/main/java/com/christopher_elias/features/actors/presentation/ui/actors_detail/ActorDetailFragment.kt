package com.christopher_elias.features.actors.presentation.ui.actors_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.christopher_elias.features.actors.R
import com.christopher_elias.features.actors.databinding.FragmentActorDetailBinding
import com.christopher_elias.features.actors.presentation.model.ActorUi

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

class ActorDetailFragment : Fragment(R.layout.fragment_actor_detail) {

    private var _binding: FragmentActorDetailBinding? = null
    private val binding: FragmentActorDetailBinding
        get() = _binding!!

    private val actor: ActorUi by lazy { requireArguments().getParcelable("actor")!! }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActorDetailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderView()
    }

    private fun renderView() {
        binding.tvActorName.text = actor.name
        binding.ivActorProfileImage.load("https://image.tmdb.org/t/p/w185/${actor.profilePath}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}