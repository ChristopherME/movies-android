package com.christopher_elias.utils

/*
 * Created by Christopher Elias on 3/05/2021
 * christopher.elias@loop-ideas.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

interface ResourceProvider {
    fun getString(resourceId: Int): String
}