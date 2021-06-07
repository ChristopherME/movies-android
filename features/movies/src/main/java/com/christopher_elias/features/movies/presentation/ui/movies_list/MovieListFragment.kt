package com.christopher_elias.features.movies.presentation.ui.movies_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.christopher_elias.common.models.presentation.MovieUi
import com.christopher_elias.features.movies.R
import com.christopher_elias.features.movies.databinding.FragmentMovieListBinding
import com.christopher_elias.features.movies.mvi_core.MviView
import com.christopher_elias.features.movies.presentation.ui.movies_detail.MovieDetailBottomSheetFragment
import com.christopher_elias.features.movies.presentation.ui.movies_list.intent.MovieListIntent
import com.christopher_elias.utils.consumeOnce
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@FlowPreview
@ExperimentalCoroutinesApi
class MovieListFragment : Fragment(R.layout.fragment_movie_list),
    MviView<MovieListIntent, MovieListUiState> {

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
        processIntents()
        collectUiState()
    }

    override fun intents(): Flow<MovieListIntent> {
        /*
         * This approach of merge flows it's taken from Etienne Caron in
         * https://github.com/kanawish/upvote/blob/3d06158db2d01af1b881dfbc91500ca147bd3591/app/src/main/java/com/kanawish/upvote/view/MainActivity.kt#L85
         */
        val flowIntents = listOf(
            binding.swipeRefreshMovies.flowRefresh()
                .map { MovieListIntent.SwipeOnRefresh }
        )
        return flowIntents.asFlow().flattenMerge(flowIntents.size)
    }

    private fun initView() {
        binding.rvMovies.adapter = MovieListAdapter { movie ->
            navigateToMovieDetail(movie)
        }
    }

    private fun processIntents() {
        intents()
            .onEach { intent -> moviesViewModel.processIntents(intent) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            moviesViewModel.uiState.collect { state ->
                render(state)
            }
        }
    }

    private fun navigateToMovieDetail(movie: MovieUi) {
        MovieDetailBottomSheetFragment()
            .apply {
                arguments = Bundle().apply {
                    putParcelable("movie", movie)
                }
            }
            .show(childFragmentManager, "MovieDetail")
    }

    override fun render(state: MovieListUiState) {
        with(state) {
            // SwipeRefreshProgress
            binding.swipeRefreshMovies.isRefreshing = isLoading

            // Bind movies.
            (binding.rvMovies.adapter as MovieListAdapter)
                .submitList(movies)

            // Empty view
            binding.tvMoviesEmpty.isVisible = !isLoading && movies.isEmpty()

            // Display or Handle error if any. Only once.
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}