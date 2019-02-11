package de.bornholdtlee.baseproject.examples.extending

import de.bornholdtlee.baseproject.utils.Logger

abstract class BaseClass {

    val name: String
    abstract var posX: Int
    abstract var posY: Int

    abstract fun doStuff()

    constructor() {
        name = "Default name"
    }

    constructor(name: String) {
        this.name = name
    }

    open fun openMethod() {
        Logger.info("Open method")
    }

    fun sealedMethod() {
        Logger.info("Sealed method")
    }
}