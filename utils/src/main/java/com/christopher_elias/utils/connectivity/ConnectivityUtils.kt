package com.christopher_elias.utils.connectivity

/*
 * Created by Christopher Elias on 22/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

interface ConnectivityUtils {
    /**
     * @return TRUE if client is connected to Wife or Cell data.
     */
    fun isNetworkAvailable(): Boolean
}