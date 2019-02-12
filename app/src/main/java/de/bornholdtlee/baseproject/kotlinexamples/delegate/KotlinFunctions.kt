package de.bornholdtlee.baseproject.kotlinexamples.delegate

import de.bornholdtlee.baseproject.utils.Logger

interface KotlinFunctions {

//    @JvmDefault <-- Experimental
    fun printStuff() {
        Logger.info("Function printed stuff")
    }

    fun doStuff() {
        Logger.info("Function did kotlin stuff")
    }

    fun doNothing() {
        //nothing
    }
}