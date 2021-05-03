package com.christopher_elias.actors.data.data_source

import com.christopher_elias.actors.data_source.models.ActorsResponse
import com.christopher_elias.functional_programming.Either
import com.christopher_elias.functional_programming.Failure

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.elias@loop-ideas.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

interface ActorsRemoteDataSource {

    suspend fun getActors(): Either<Failure, List<ActorsResponse>>
}