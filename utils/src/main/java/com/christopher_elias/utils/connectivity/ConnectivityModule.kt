package com.christopher_elias.utils.connectivity

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

abstract class ConnectivityModule {
    internal abstract fun bindConnectivityUtils(
        connectivityUtilsImpl: ConnectivityUtilsImpl
    ): ConnectivityUtils
}