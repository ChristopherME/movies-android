package com.christopher_elias.features.actors.domain.repository

import com.christopher_elias.features.actors.domain.models.Actor
import com.christopher_elias.functional_programming.Either
import com.christopher_elias.functional_programming.Failure

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

interface ActorsRepository {

    suspend fun getActors(): Either<Failure, List<Actor>>

}