package com.christopher_elias.movies.resource_provider

import android.content.Context
import com.christopher_elias.utils.resource_provider.ResourceProvider

/*
 * Created by Christopher Elias on 3/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class ResourceProviderImpl(
    private val context: Context
) : ResourceProvider {

    override fun getString(resourceId: Int): String = context.getString(resourceId)

    override fun getString(
        resourceId: Int,
        vararg args: Any
    ): String {
        return if (args.isNotEmpty()) {
            context.resources.getString(resourceId, *args)
        } else {
            context.resources.getString(resourceId)
        }
    }

}