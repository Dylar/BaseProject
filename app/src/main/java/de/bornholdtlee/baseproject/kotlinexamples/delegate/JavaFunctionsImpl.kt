package de.bornholdtlee.baseproject.kotlinexamples.delegate

import de.bornholdtlee.baseproject.utils.Logger

class JavaFunctionsImpl : JavaFunctions {
    override fun doStuff() {
        Logger.info("Function did java stuff")
    }

    override fun doMoreStuff(): Boolean {
        Logger.info("Function did more java Stuff")
        return true
    }
}
