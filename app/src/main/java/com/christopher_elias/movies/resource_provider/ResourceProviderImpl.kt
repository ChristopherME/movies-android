package com.christopher_elias.movies.resource_provider

import android.content.Context
import com.christopher_elias.utils.resource_provider.ResourceProvider

/*
 * Created by Christopher Elias on 3/05/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

class ResourceProviderImpl(
    private val context: Context
) : ResourceProvider {
    override fun getString(resourceId: Int): String = context.getString(resourceId)
}