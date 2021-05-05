package com.christopher_elias.features.movies.mapper

import com.christopher_elias.features.movies.data_source.model.MovieResponse
import com.christopher_elias.features.movies.domain.model.Movie
import com.christopher_elias.features.movies.presentation.model.MovieUi

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

interface MovieMapper {

    suspend fun mapRemoteMoviesListToDomain(remoteMovies: List<MovieResponse>): List<Movie>

    suspend fun mapRemoteMovieToDomain(remoteMovie: MovieResponse): Movie

    suspend fun mapDomainMoviesListToUi(domainMovies: List<Movie>): List<MovieUi>

    suspend fun mapDomainMovieToUi(domainMovie: Movie): MovieUi


}