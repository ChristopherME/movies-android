package com.christopher_elias.common.models.mapper

import com.christopher_elias.common.models.data.MovieResponse
import com.christopher_elias.common.models.domain.Movie
import com.christopher_elias.common.models.presentation.MovieUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class MovieMapperImpl(
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
            image = remoteMovie.image ?: remoteMovie.backdropImage ?: "",
            title = remoteMovie.title ?: remoteMovie.originalTitle
            ?: remoteMovie.originalTitleAlternative
            ?: "No title found",
            overview = remoteMovie.overview,
            releaseDate = remoteMovie.releaseDate ?: remoteMovie.releaseDateAlternative
            ?: "No date found",
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
            title = domainMovie.title,
            overview = domainMovie.overview,
            releaseDate = domainMovie.releaseDate,
            originalLanguage = domainMovie.originalLanguage
        )
    }

}