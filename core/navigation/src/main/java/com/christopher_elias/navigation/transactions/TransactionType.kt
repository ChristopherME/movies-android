package com.christopher_elias.navigation.transactions

/*
 * Created by Christopher Elias on 29/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

enum class TransactionType {

    /**
     * Add fragment on top of the current fragment that call this method.
     */
    ADD_FRAGMENT,

    /**
     * Replace the current fragment for a new fragment that call this method.
     */
    REPLACE_FRAGMENT
}