package com.christopher_elias.features.actors.presentation.ui.actors_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.christopher_elias.common.models.presentation.MovieUi
import com.christopher_elias.features.actors.R
import com.christopher_elias.features.actors.databinding.FragmentActorDetailBinding
import com.christopher_elias.features.actors.presentation.model.ActorUi
import com.christopher_elias.features.actors.presentation.ui.actors_detail.adapter.ActorKnownForAdapter
import com.christopher_elias.navigation.loadFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class ActorDetailFragment : Fragment(R.layout.fragment_actor_detail) {

    private var _binding: FragmentActorDetailBinding? = null
    private val binding: FragmentActorDetailBinding
        get() = _binding!!

    private val actor by lazy { requireArguments().getParcelable<ActorUi>("actor")!! }

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
        with(actor) {
            binding.tvActorName.text = name

            binding.ivActorProfileImage.load(profilePath) {
                fallback(R.drawable.ic_failure)
            }

            binding.rvMovies.setHasFixedSize(true)
            binding.rvMovies.adapter = ActorKnownForAdapter(movies = knownFor, ::showMovieDetail)
        }
    }

    private fun showMovieDetail(movie: MovieUi) {
        (loadFragment("com.christopher_elias.features.movies.presentation.ui.movies_detail.MovieDetailBottomSheetFragment") as BottomSheetDialogFragment).apply {
            arguments = Bundle().apply {
                putParcelable("movie", movie)
            }
        }.show(childFragmentManager, "MovieDetail")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}