package com.christopher_elias.features.actors.data.data_source

import com.christopher_elias.features.actors.data_source.models.ActorsResponse
import com.christopher_elias.functional_programming.Either
import com.christopher_elias.functional_programming.Failure

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

interface ActorsRemoteDataSource {

    suspend fun getActors(): Either<Failure, List<ActorsResponse>>
}