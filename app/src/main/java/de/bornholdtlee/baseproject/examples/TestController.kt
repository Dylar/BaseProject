package de.bornholdtlee.baseproject.examples

import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.controller.BaseController
import de.bornholdtlee.baseproject.utils.Logger

class TestController(application: BaseApplication) : BaseController(application) {

    companion object {
        val TEST_IS_IN_WHEN = 0
        val TEST_LOOPINGS = TEST_IS_IN_WHEN + 1
        val TEST_BREAK_IT = TEST_LOOPINGS + 1
        val TEST_SUBCLASS = TEST_BREAK_IT + 1

        val MAX_TEST = TEST_SUBCLASS

        const val BREAK_STRING = "breakIt"

    }

    val kotlinAny: KotlinAny = KotlinAny()
    val javaObject: JavaObject = JavaObject()

    fun getTestName(index: Int): String {
        return "Test: ${when (index) {
            TEST_IS_IN_WHEN -> "Is in when"
            TEST_LOOPINGS -> "Loopings"
            TEST_BREAK_IT -> "Break it"
            TEST_SUBCLASS -> "Subclass"
            else -> ""
        }}"
    }

    fun doIt(counter: Int) {
        Logger.info("TEST: ${getTestName(counter)}")
        when (counter) {
            TEST_IS_IN_WHEN -> {
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
            TEST_LOOPINGS -> {
                kotlinAny.doLooping("Ich", "bin", "eine", "Liste")
            }
            TEST_BREAK_IT -> {
                kotlinAny.breakIt(values = *arrayOf("returnFunction"))
                val list = arrayListOf("Ich", "bin", "eine", "Liste")
                kotlinAny.breakIt(values = *list.toTypedArray())
            }
            TEST_SUBCLASS -> {

                kotlinAny.doLooping("Ich", "bin", "eine", "Liste")
            }
        }
    }

    private fun doIsInWhenTest(value: Any) {
        kotlinAny.isInWhen(value)
        javaObject.isInWhen(value)
    }
}