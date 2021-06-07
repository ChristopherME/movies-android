package com.christopher_elias.features.movies.presentation.ui.movies_list.processor

import com.christopher_elias.common.models.mapper.MovieMapper
import com.christopher_elias.common.models.presentation.MovieUi
import com.christopher_elias.features.movies.domain.MoviesRepository
import com.christopher_elias.features.movies.mvi_core.MviProcessorHolder
import com.christopher_elias.features.movies.presentation.ui.movies_list.action.MovieListAction
import com.christopher_elias.features.movies.presentation.ui.movies_list.result.MovieListResult
import com.christopher_elias.functional_programming.Either
import com.christopher_elias.functional_programming.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/*
 * Created by Christopher Elias on 14/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class MovieListProcessorHolder(
    private val moviesRepository: MoviesRepository,
    private val moviesMapper: MovieMapper
) : MviProcessorHolder<MovieListAction, MovieListResult> {

    override fun processAction(action: MovieListAction): Flow<MovieListResult> {
        return flow {
            // Get the items from repository
            when (action) {
                MovieListAction.LoadMoviesAction -> {
                    // First send a loading result
                    emit(MovieListResult.Loading)
                    val response: Either<Failure, List<MovieUi>> =
                        moviesRepository.getMovies()
                            .coMapSuccess { domainMovies ->
                                moviesMapper.mapDomainMoviesListToUi(domainMovies)
                            }
                    val result = handleSuccessOrFailure(result = response)
                    emit(result)
                }
            }
        }
    }

    private fun handleSuccessOrFailure(result: Either<Failure, List<MovieUi>>): MovieListResult {
        return when (result) {
            is Either.Error -> MovieListResult.Error(failure = result.error)
            is Either.Success -> MovieListResult.Success(movies = result.success)
        }
    }

}