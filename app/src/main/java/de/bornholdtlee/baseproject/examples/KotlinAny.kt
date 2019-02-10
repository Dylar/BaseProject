package de.bornholdtlee.baseproject.examples

import de.bornholdtlee.baseproject.examples.TestController.Companion.BREAK_STRING
import de.bornholdtlee.baseproject.utils.Logger

open class KotlinAny : TestMethods {

    private lateinit var loadedImage: ByteArray

    fun loadImage(imagePath: String = "") {
        loadedImage = ImageUtils.loadImage(imagePath)
    }

    fun showImage() {

    }

    fun calculateMax(value1: Int = 0, value2: Int = 0) {
        val maxValue = if (value1 > value2) {
            value1
        } else {
            value2
        }
        Logger.info("MaxValue is $maxValue")
    }

    override fun isInWhen(value: Any): Boolean {
        Logger.info("Value is: $value")
        return when (value) {
            0.toDouble() -> {
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
                Logger.info("$value is 6 oder 7")
                true
            }
            in 0..100 -> {
                Logger.info("$value is in Range")
                true
            }
            in 101..104 step 2 -> {
                Logger.info("$value is 101 oder 103")
                true
            }
            is String -> {
                Logger.info("String: $value")
                true
            }
            else -> false
        }
    }

    fun doLooping(vararg values: String) {
        Logger.info("Values")
        for (value in values) {
            Logger.info("Value: $value")
        }
        Logger.info("Values with Index")
        for (value in values.withIndex()) {
            Logger.info("Index: ${value.index}, Value: ${value.value}")
        }

        Logger.info("Value in range")
        val range = 0..values.lastIndex
        for (index in range) {
            Logger.info("Value: ${values[index]}")
        }

        Logger.info("Value reversed")
        val reversed = values.lastIndex downTo 0
        for (index in reversed) {
            Logger.info("Value: ${values[index]}")
        }

    }


    fun breakIt(break1: Int = 2, break2: Int = 3, vararg values: String) {
        Logger.info("For each loop")
        values.forEach {
            Logger.info("For each value: $it")
            if (it == "eine") {
                Logger.info("return for each")
                return@forEach
            }
            if (it == BREAK_STRING) {
                Logger.info("return function")
                return@breakIt
//                return <-- or just return
            }
        }

        Logger.info("Inner/Outer loop")
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
            }
        }


    }


}