package de.bornholdtlee.baseproject.kotlinexamples.control

import de.bornholdtlee.baseproject.kotlinexamples.control.JavaObject
import de.bornholdtlee.baseproject.kotlinexamples.control.KotlinAny
import de.bornholdtlee.baseproject.utils.Logger

class KotlinConverted {

    fun isInWhen(value: Any): Boolean {
        Logger.error("Value is: $value")
        var isIn = true
        if (value is Double && value == 0.0) {
            Logger.info("Double: " + (value is Double))
        } else if (value is Long && value == 0L) {
            Logger.info("Long: " + (value is Long))
        } else if (value is Float && value == 100f) {
            Logger.info("Long: " + (value is Float))
        } else if (value is Int && (value == 6 || value == 7)) {
            Logger.info("$value is 6 oder 7")
        } else if (value is Int && value <= 100 && value >= 0) {
            Logger.info("$value is in Range 0 to 100")
        } else if (value is Int && (value == 101 || value == 103)) { //<-- Not the same
            Logger.info("$value is 101 or 103")
        } else if (value is String) {
            Logger.info("String: $value")
        } else if (value is JavaObject) {
            Logger.info("Is JavaDelegation: " + value.javaObjectName)
        } else if (value is KotlinAny) {
            Logger.info("Is KotlinDelegation: " + value.getKotlinObjectName())
        } else if (!(Integer.valueOf(-2) == value && Integer.valueOf(-1) == value)) {  //<-- Not the same
            Logger.info("Value is not in Range -2 to -1")
        } else {
            Logger.info("only -2 or -1")
            isIn = false
        }
        return isIn
    }
}
