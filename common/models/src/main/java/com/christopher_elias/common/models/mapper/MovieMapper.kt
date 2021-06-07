package com.christopher_elias.common.models.mapper

import com.christopher_elias.common.models.data.MovieResponse
import com.christopher_elias.common.models.domain.Movie
import com.christopher_elias.common.models.presentation.MovieUi

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

interface MovieMapper {

    suspend fun mapRemoteMoviesListToDomain(remoteMovies: List<MovieResponse>): List<Movie>

    suspend fun mapRemoteMovieToDomain(remoteMovie: MovieResponse): Movie

    suspend fun mapDomainMoviesListToUi(domainMovies: List<Movie>): List<MovieUi>

    suspend fun mapDomainMovieToUi(domainMovie: Movie): MovieUi


}