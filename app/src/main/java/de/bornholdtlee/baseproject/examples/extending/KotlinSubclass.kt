package de.bornholdtlee.baseproject.examples.extending

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
    }

    constructor(name: String) : super(name) {
        init()
    }

    private fun init() {
        Logger.info("Do additional constructor stuff")
    }

    override fun doStuff() {
        Logger.info("Kotlin sub stuff")
    }

    override fun openMethod() {
        super.openMethod()
        Logger.info("In Kotlin")
    }
}