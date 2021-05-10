package com.christopher_elias.features.movies.presentation.ui.movies_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.christopher_elias.features.movies.R
import com.christopher_elias.features.movies.databinding.FragmentMovieListBinding
import com.christopher_elias.features.movies.presentation.ui.movies_list.adapter.MovieListAdapter
import com.christopher_elias.features.movies.presentation.ui.movies_list.adapter.MovieLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

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
    private var adapter: MovieListAdapter? = null

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
        adapter = MovieListAdapter()

        binding.rvMovies.adapter = adapter?.withLoadStateHeaderAndFooter(
            header = MovieLoadStateAdapter { adapter?.retry() },
            footer = MovieLoadStateAdapter { adapter?.retry() }
        )

        adapter?.addLoadStateListener { loadState ->
            val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter?.itemCount == 0
            showEmptyList(isEmpty = isListEmpty)

            // Only shows the list if refresh succeeds.
            binding.rvMovies.isVisible = loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            binding.progressBarMovies.isVisible = loadState.source.refresh is LoadState.Loading
            // Show the retry state if initial load or refresh fails.
            binding.btnMoviesRetry.isVisible = loadState.source.refresh is LoadState.Error
        }

        binding.btnMoviesRetry.setOnClickListener { adapter?.retry() }

    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            moviesViewModel.getMovies().collectLatest { movies ->
                // (binding.rvMovies.adapter as MovieListAdapter)
                adapter?.submitData(movies)
            }
        }
    }

    private fun showEmptyList(isEmpty: Boolean) {
        binding.rvMovies.isVisible = !isEmpty
        binding.tvMoviesEmpty.isVisible = isEmpty
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
        _binding = null
    }
}