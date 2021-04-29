package com.christopher_elias.movies.mapper

import com.christopher_elias.movies.data_source.model.MovieResponse
import com.christopher_elias.movies.domain.model.Movie
import com.christopher_elias.movies.presentation.model.MovieUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

internal class MovieMapperImpl(
    private val defaultDispatcher: CoroutineDispatcher
) : MovieMapper {

    override suspend fun mapRemoteMoviesListToDomain(
        remoteMovies: List<MovieResponse>
    ): List<Movie> {
        return withContext(defaultDispatcher) {
            remoteMovies.map {
                mapRemoteMovieToDomain(it)
            }
        }
    }

    override suspend fun mapRemoteMovieToDomain(
        remoteMovie: MovieResponse
    ): Movie {
        return Movie(
            id = remoteMovie.id,
            isAdultOnly = remoteMovie.isAdultOnly,
            popularity = remoteMovie.popularity,
            voteAverage = remoteMovie.voteAverage,
            voteCount = remoteMovie.voteCount,
            image = remoteMovie.image,
            backdropImage = remoteMovie.backdropImage,
            title = remoteMovie.title,
            overview = remoteMovie.overview,
            releaseDate = remoteMovie.releaseDate,
            originalTitle = remoteMovie.originalTitle,
            originalLanguage = remoteMovie.originalLanguage
        )
    }

    override suspend fun mapDomainMoviesListToUi(
        domainMovies: List<Movie>
    ): List<MovieUi> {
        return withContext(defaultDispatcher) {
            domainMovies.map {
                mapDomainMovieToUi(it)
            }
        }
    }

    override suspend fun mapDomainMovieToUi(
        domainMovie: Movie
    ): MovieUi {
        return MovieUi(
            id = domainMovie.id,
            isAdultOnly = domainMovie.isAdultOnly,
            popularity = domainMovie.popularity,
            voteAverage = domainMovie.voteAverage,
            voteCount = domainMovie.voteCount,
            image = domainMovie.image,
            backdropImage = domainMovie.backdropImage,
            title = domainMovie.title,
            overview = domainMovie.overview,
            releaseDate = domainMovie.releaseDate,
            originalTitle = domainMovie.originalTitle,
            originalLanguage = domainMovie.originalLanguage
        )
    }

}