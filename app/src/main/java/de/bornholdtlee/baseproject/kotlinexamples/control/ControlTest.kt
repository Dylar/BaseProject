package de.bornholdtlee.baseproject.kotlinexamples.control

import de.bornholdtlee.baseproject.kotlinexamples.TestController

object ControlTest {

    private val kotlinAny: KotlinAny = KotlinAny()
    private val javaObject: JavaObject = JavaObject()

    fun doHandleList() {
        kotlinAny.handleLists(mutableListOf("Ich", "bin", "eine", "Liste"))
    }

    fun doLooping() {
        kotlinAny.doLooping("Ich", "bin", "eine", "Liste")
    }

    fun doBreakit() {
        kotlinAny.breakIt(values = *arrayOf(TestController.BREAK_STRING))
        val list = arrayListOf("Ich", "bin", "eine", "Liste")
        kotlinAny.breakIt(values = *list.toTypedArray())
    }

    fun doIsWhen() {

        val doIsInWhenFunction = fun(value: Any) {
            kotlinAny.isInWhen(value)
            javaObject.isInWhen(value)
        }

        doIsInWhenFunction(0.0)
        doIsInWhenFunction(0L)
        doIsInWhenFunction(100f)
        doIsInWhenFunction(6)
        doIsInWhenFunction(7)
        doIsInWhenFunction(69)
        doIsInWhenFunction(99)
        doIsInWhenFunction(100)

        doIsInWhenFunction(101)
        doIsInWhenFunction(102)
        doIsInWhenFunction(103)

        doIsInWhenFunction("IsnString")

        doIsInWhenFunction(kotlinAny)
        doIsInWhenFunction(javaObject)
    }


}