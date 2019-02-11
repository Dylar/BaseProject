package de.bornholdtlee.baseproject.examples.delegate

import de.bornholdtlee.baseproject.utils.Logger

interface KotlinFunctions {

    fun printStuff() {
        Logger.info("Function printed stuff")
    }

    fun doStuff() {
        Logger.info("Function did stuff")
    }

    fun doNothing() {
        //nothing
    }
}