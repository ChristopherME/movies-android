package com.christopher_elias.features.movies.presentation.ui.movies_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import coil.transform.RoundedCornersTransformation
import com.christopher_elias.common.models.presentation.MovieUi
import com.christopher_elias.features.movies.databinding.BottomFragmentMovieDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class MovieDetailBottomSheetFragment : BottomSheetDialogFragment() {

    private val movie by lazy { requireArguments().getParcelable<MovieUi>("movie")!! }

    private var _binding: BottomFragmentMovieDetailBinding? = null
    private val binding: BottomFragmentMovieDetailBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomFragmentMovieDetailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderView()
        setClickListeners()
    }

    private fun renderView() {
        with(movie) {
            binding.tvMovieName.text = title
            binding.tvMovieOverview.text = overview
            binding.tvMovieReleaseDate.text = releaseDate
            binding.ivMoviePoster.load("https://image.tmdb.org/t/p/w500/$image") {
                crossfade(durationMillis = 1500)
                transformations(RoundedCornersTransformation(12.5f))
            }
        }
    }

    private fun setClickListeners() {
        binding.ivMovieDetailClose.setOnClickListener { dismiss() }
        binding.btnMoviePlay.setOnClickListener { }
        binding.tvMovieInfo.setOnClickListener { }
        binding.tvMovieDownload.setOnClickListener { }
        binding.tvMovieAdvance.setOnClickListener { }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}