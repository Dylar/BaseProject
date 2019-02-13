package de.bornholdtlee.baseproject.kotlinexamples.delegate

import de.bornholdtlee.baseproject.utils.Logger

interface KotlinFunctions {

//    @JvmDefault <-- Experimental
    fun printStuff() {
        Logger.info("KotlinFunctions printed stuff")
    }

    fun doStuff() {
        Logger.info("KotlinFunctions did kotlin stuff")
    }

    fun doNothing() {
        Logger.info("KotlinFunctions did nothing")
        //nothing
    }
}