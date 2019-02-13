package de.bornholdtlee.baseproject.kotlinexamples.extending

import de.bornholdtlee.baseproject.utils.Logger

class KotlinSubclass : BaseClass {
    override var posX: Int = 0
        get() = field - 1
        set(value) {
            field = value + 1
        }

    override var posY: Int = 0

    constructor() : super() {
        init()
        methodWithDefault(parameter2 = "Second Value")
    }

    constructor(name: String) : super(name) {
        init()
        methodWithDefault(parameter2 = name)
    }

    private fun init() {
        Logger.info("KotlinSubclass did additional constructor stuff")
    }

    override fun doStuff() {
        Logger.info("Kotlin sub stuff")
    }

    override fun openMethod() {
        super.openMethod()
        Logger.info("In Kotlin")
    }
}