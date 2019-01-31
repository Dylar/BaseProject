package de.bornholdtlee.baseproject.examples.delegate

import android.graphics.Point

interface KotlinRotationInterface {

    fun getRotationPoint(): Point {
        return Point(0, 0)
    }
}