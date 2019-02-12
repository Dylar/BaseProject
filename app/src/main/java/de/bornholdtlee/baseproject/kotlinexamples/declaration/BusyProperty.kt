package de.bornholdtlee.baseproject.kotlinexamples.declaration

import de.bornholdtlee.baseproject.utils.Logger
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class BusyProperty : ReadWriteProperty<KotlinDeclaration, Int> {
    private var busyCounter: Int = 0
    override fun getValue(thisRef: KotlinDeclaration, property: KProperty<*>): Int {
        Logger.info("Busy called always")
        busyCounter++
        return busyCounter
    }

    override fun setValue(thisRef: KotlinDeclaration, property: KProperty<*>, value: Int) {

    }

}
