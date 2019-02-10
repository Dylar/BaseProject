package de.bornholdtlee.baseproject.examples.extending

import de.bornholdtlee.baseproject.utils.Logger

class KotlinSubclass : BaseClass() {

    override var posX: Int = 0
        get() = field - 1
        set(value) {
            field = value + 1
        }

    override var posY: Int = 0

    override fun doStuff() {
        Logger.info("Kotlin stuff")
    }

    override fun getName(): String {
        return "Kotlin Sub"
    }
}