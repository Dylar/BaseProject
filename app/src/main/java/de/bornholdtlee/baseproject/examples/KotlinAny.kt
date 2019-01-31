package de.bornholdtlee.baseproject.examples

import de.bornholdtlee.baseproject.utils.Logger
import javax.inject.Inject

open class KotlinAny(

) {

    @Inject
    lateinit var anyController: AnyController

    private lateinit var loadedImage: ByteArray

    init {

    }

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

    fun isInWhen(value: Any): Boolean {
        Logger.info("Value is: $value")
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


}