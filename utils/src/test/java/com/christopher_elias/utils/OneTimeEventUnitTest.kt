package com.christopher_elias.utils

import org.junit.Test
import org.junit.Assert.*

/*
 * Created by Christopher Elias on 26/04/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */


class OneTimeEventUnitTest {


    @Test
    fun `Assert item is consumed just once`() {
        var timesCalledInConsumeOnce = 0
        val result = SomeResult(1, "1".toOneTimeEvent())
        // We have obtain the value, therefore a second call should throw null.

        result.failure.consumeOnce {
            timesCalledInConsumeOnce +=1
            println("Im consumed!")
            assertEquals(
                "1",
                it
            )
        }

        assertEquals(
            1,
            timesCalledInConsumeOnce
        )

        println("Ups")
        result.failure.consumeOnce { println("never reached!") }
        println("Nothing")

        assertNull(result.failure?.getValue())

        val consumedValue = result.failure.toString()
        println("consumed: $consumedValue")

        val newValue = "1".toOneTimeEvent().toString()
        println("new value: $newValue")

        assertEquals(
            consumedValue,
            newValue
        )
        assertNotEquals(
            result.failure?.getValue(),
            "1".toOneTimeEvent().getValue()
        )

        assertEquals(
            1,
            timesCalledInConsumeOnce
        )

    }

    data class SomeResult(
        val id: Int,
        val failure: OneTimeEvent<String>?
    )
}