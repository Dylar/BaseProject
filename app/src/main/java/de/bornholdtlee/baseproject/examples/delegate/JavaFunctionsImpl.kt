package de.bornholdtlee.baseproject.examples.delegate

import de.bornholdtlee.baseproject.utils.Logger

class JavaFunctionsImpl : JavaFunctions {
    override fun moreStuff() {
        Logger.info("More Stuff")
    }

    override fun doMoreStuff(): Boolean {
        Logger.info("Did more Stuff")
        return true
    }
}
