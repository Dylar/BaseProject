package de.bornholdtlee.baseproject.kotlinexamples.extending

import de.bornholdtlee.baseproject.utils.Logger

abstract class BaseClass {

    val name: String
    abstract var posX: Int
    abstract var posY: Int

    constructor() {
        name = "Default name"
    }

    constructor(name: String) {
        this.name = name
    }

    abstract fun doStuff()

    open fun openMethod() {
        Logger.info("Open method")
    }

    fun finalMethod() {
        Logger.info("Final method")
    }

    @JvmOverloads
    fun methodWithDefault(parameter1: String = "DefaultValue", parameter2: String) {
        Logger.info("Parameter1: $parameter1, Parameter2: $parameter2")
    }
}