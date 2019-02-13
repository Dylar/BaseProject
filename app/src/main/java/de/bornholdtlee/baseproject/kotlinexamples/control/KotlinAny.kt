package de.bornholdtlee.baseproject.kotlinexamples.control

import de.bornholdtlee.baseproject.kotlinexamples.TestController.Companion.BREAK_STRING
import de.bornholdtlee.baseproject.utils.Logger

class KotlinAny {

    fun getKotlinObjectName(): String {
        return "Kotlin Object"
    }

    fun isInWhen(value: Any): Boolean {
        Logger.error("Value is: $value")
        return when (value) {
            0.0 -> {
                Logger.info("Double: ${value is Double}")
                true
            }
            0L -> {
                Logger.info("Long: ${value is Long}")
                true
            }
            100f -> {
                Logger.info("Float: ${value is Float}")
                true
            }
            6, 7 -> {
                Logger.info("$value is 6 or 7")
                true
            }
            in 0..100 -> {
                Logger.info("$value is in Range 0 to 100")
                true
            }
            in 101..104 step 2 -> {
                Logger.info("$value is 101 or 103")
                true
            }
            is String -> {
                Logger.info("String: $value")
                true
            }
//            is JavaObject -> {
//                Logger.info("Is JavaDelegation: ${value.javaObjectName}")
//                true
//            }
            is KotlinAny, is JavaObject -> {
                Logger.info("Is KotlinDelegation: ${(value as KotlinAny).getKotlinObjectName()}")
                true
            }
            !in -2..-1 -> {
                Logger.info("Value is not in Range -2 to -1")
                true
            }
            else -> {
                Logger.info("only -2 or -1")
                false
            }
        }
    }

    fun doLooping(vararg values: String) {
        Logger.error("Print values")
        for (value in values) {
            Logger.info("Value: $value")
        }
        Logger.error("Print values with Index")
        for (value in values.withIndex()) {
            Logger.info("Index: ${value.index}, Value: ${value.value}")
        }

        Logger.error("Print value in range")
        val range = 0..values.lastIndex
        for (index in range) {
            Logger.info("Value: ${values[index]}")
        }

        Logger.error("Print value with steps")
        for (index in range step 2) {
            Logger.info("Value: ${values[index]}")
        }

        Logger.error("Print value reversed")
        val reversed = values.lastIndex downTo 0
        for (index in values.lastIndex downTo 0 step 2) {
            Logger.info("Value: ${values[index]}")
        }
    }

    fun breakIt(break1: Int = 2, break2: Int = 3, vararg values: String) {
        Logger.error("For each loop")
        values.forEach {
            Logger.info("For each value: $it")
            if (it == "eine") {
                Logger.info("return for each - Still running -> like a continue")
                return@forEach
            } else if (it == BREAK_STRING) {
                Logger.info("return function")
                return@breakIt
//                return <-- or just return
            }
            Logger.info("Block done")
        }

        Logger.error("Inner/Outer loop")
        outerLoop@ for (value1 in 0..4) {
            if (break1 == value1) {
                Logger.info("break loop1: $value1")
                break@outerLoop
            }
            innerLoop@ for (value2 in 0..4) {
                if (break2 == value2) {
                    Logger.info("break loop2: $value2")
                    break@innerLoop
                }
                break@outerLoop
            }
        }

    }

    fun handleLists(list: List<String>) {
        Logger.error("Listen test")

        Logger.info("First: ${list.first()}")
        Logger.info("Last: ${list.last()}")
        Logger.info("Not empty: ${list.isNotEmpty()}")
        Logger.info("Empty or null: ${list.isNullOrEmpty()}")

        val filtered = list.filter { it.length < 4 }
        filtered.forEach { Logger.info("Small value: $it") }

        if (filtered.none { it.length >= 3 }) {
            Logger.info("Contains no value length >= 3")
        } else {
            Logger.info("Contains value length >= 3")
        }

        if (filtered.isNotEmpty()) {
//          list.add(filtered.first())
            val mutableList = ArrayList<String>()
            mutableList.add(filtered.first())
        }
    }
}