package com.christopher_elias.features.actors

import com.christopher_elias.utils.resource_provider.ResourceProvider

/*
 * Created by Christopher Elias on 4/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class MockResourceProviderImpl : ResourceProvider {
    override fun getString(resourceId: Int): String = ""

    override fun getString(resourceId: Int, vararg args: Any): String {
        return if (args[0] is String) {
            (args[0] as String)
        } else ""
    }
}