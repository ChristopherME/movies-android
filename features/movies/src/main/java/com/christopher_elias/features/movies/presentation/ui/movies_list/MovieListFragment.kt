package com.christopher_elias.features.movies.presentation.ui.movies_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.christopher_elias.features.movies.R
import com.christopher_elias.features.movies.databinding.FragmentMovieListBinding
import com.christopher_elias.features.movies.presentation.model.MovieUi
import com.christopher_elias.features.movies.presentation.ui.movies_detail.MovieDetailBottomSheetFragment
import com.christopher_elias.features.movies.presentation.ui.movies_list.adapter.MovieListAdapter
import com.christopher_elias.utils.consumeOnce
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private var _binding: FragmentMovieListBinding? = null
    private val binding: FragmentMovieListBinding
        get() = _binding!!

    private val moviesViewModel: MovieListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        collectUiState()
    }

    private fun initView() {
        binding.rvMovies.adapter = MovieListAdapter(clickListener = ::onMovieClicked)
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            moviesViewModel.uiState.collect { state ->
                renderUiState(state)
            }
        }
    }

    private fun renderUiState(state: MovieListUiState) {
        with(state) {
            // Progress
            binding.progressBarMovies.isVisible = isLoading

            // Bind movies.
            (binding.rvMovies.adapter as MovieListAdapter)
                .submitList(movies)

            // Empty view
            binding.tvMoviesEmpty.isVisible = !isLoading && movies.isEmpty()

            // Display error if any. Only once.
            error?.let {
                it.consumeOnce { failure ->
                    Toast.makeText(
                        requireContext(),
                        "$failure",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun onMovieClicked(movie: MovieUi) {
        Timber.d("Movie: $movie")
        MovieDetailBottomSheetFragment().apply {
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