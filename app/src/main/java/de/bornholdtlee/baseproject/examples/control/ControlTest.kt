package de.bornholdtlee.baseproject.examples.control

object ControlTest {

    private val kotlinAny: KotlinAny = KotlinAny()
    private val javaObject: JavaObject = JavaObject()

    fun doIsWhen() {

        val doIsInWhenTest = fun(value: Any) {
            kotlinAny.isInWhen(value)
            javaObject.isInWhen(value)
        }

        doIsInWhenTest(0.0)
        doIsInWhenTest(0L)
        doIsInWhenTest(100f)
        doIsInWhenTest(6)
        doIsInWhenTest(7)
        doIsInWhenTest(69)
        doIsInWhenTest(99)
        doIsInWhenTest(100)

        doIsInWhenTest(101)
        doIsInWhenTest(103)

        doIsInWhenTest("IsnString")

        doIsInWhenTest(kotlinAny)
        doIsInWhenTest(javaObject)
    }


    fun doLooping() {
        kotlinAny.doLooping("Ich", "bin", "eine", "Liste")
    }

    fun doBreakit() {
        kotlinAny.breakIt(values = *arrayOf("returnFunction"))
        val list = arrayListOf("Ich", "bin", "eine", "Liste")
        kotlinAny.breakIt(values = *list.toTypedArray())
    }
}