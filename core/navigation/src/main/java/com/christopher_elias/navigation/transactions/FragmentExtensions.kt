package com.christopher_elias.navigation.transactions

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/*
 * Created by Christopher Elias on 29/05/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
 * Lima, Peru.
 */

fun Fragment.pop() = parentFragmentManager.popBackStack()

fun Fragment.popUntil(
    fragmentTag: String
) = parentFragmentManager.popBackStack(fragmentTag, FragmentManager.POP_BACK_STACK_INCLUSIVE)

/**
 * This method will replace the current fragment for the [newFragment].
 * Therefore, the old fragment that is calling this method will not be visible.
 */
fun Fragment.replaceFragmentExt(
    newFragment: Fragment,
    addToBackStack: Boolean,
    fromActivity: Boolean = true,
    containerId: Int? = null,
    transactionAnimations: TransactionAnimations = TransactionAnimations.NONE
) = this.navUtils(
    TransactionType.REPLACE_FRAGMENT,
    newFragment,
    addToBackStack,
    fromActivity,
    containerId ?: android.R.id.content,
    transactionAnimations
)

/**
 * This method will add a [newFragment] on top of the current fragment.
 * Therefore, the current fragment will be visible behind the [newFragment].
 */
fun Fragment.addFragmentExt(
    newFragment: Fragment,
    addToBackStack: Boolean,
    fromActivity: Boolean = true,
    containerId: Int? = null,
    transactionAnimations: TransactionAnimations = TransactionAnimations.NONE
) = this.navUtils(
    TransactionType.ADD_FRAGMENT,
    newFragment,
    addToBackStack,
    fromActivity,
    containerId ?: android.R.id.content,
    transactionAnimations
)

/**
 * Extension for add or replace the fragment to the container.
 * [TransactionType.ADD_FRAGMENT] - Will put the [newFragment] on top of the current fragment.
 * [TransactionType.REPLACE_FRAGMENT] - Will replace the current fragment for the [newFragment] & the current fragment would not be visible.
 *
 * @param transactionType a [TransactionType] object.
 * @param newFragment the fragment to ADD or REPLACE.
 * @param addToBackStack if true then, the [newFragment] will be added to the back stack.
 * @param fromActivity If true it means the transaction operation will be performed from the container activity, if not, will be performed from the current fragment.
 * @param containerId the VIEW container id. The ID passed here it's supposed to be the ID of the View that contains the Fragment/s. Usually a FrameLayout or a FragmentContainerView.
 * @param transactionAnimations a [TransactionAnimations] object that will determine the animation to apply to the current transaction.
 */
internal fun Fragment.navUtils(
    transactionType: TransactionType,
    newFragment: Fragment,
    addToBackStack: Boolean,
    fromActivity: Boolean,
    containerId: Int,
    transactionAnimations: TransactionAnimations
) {
    val manager = if (fromActivity) activity?.supportFragmentManager else childFragmentManager

    // This could cause problems if we have a screen that shows a product detail
    // and at the bottom a list of similar products. If the user clicks in one of the similar products,
    // another instance of the product detail fragment should have to be added, but the condition
    // from above wouldn't allow that operation. Solutions:
    // 1.- Concat the fragment tag name with something else like the productId
    // 2.- Remove this condition and handle manually that the "same" fragment is not added.
    if (manager?.findFragmentByTag(newFragment::class.java.simpleName) != null) {
        Log.w(
            "NavUtils",
            "${newFragment::class.java.simpleName} already added. Transaction aborted."
        )
        return
    }

    manager?.beginTransaction()?.fragmentTransactionExt(
        transactionType = transactionType,
        newFragment = newFragment,
        addToBackStack = addToBackStack,
        containerId = containerId,
        fragmentTag = newFragment::class.java.simpleName,
        transactionAnimations = transactionAnimations
    )
}