package de.bornholdtlee.baseproject.kotlinexamples

import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.controller.BaseController
import de.bornholdtlee.baseproject.kotlinexamples.control.ControlTest
import de.bornholdtlee.baseproject.kotlinexamples.dataclass.DataClassTest
import de.bornholdtlee.baseproject.kotlinexamples.declaration.DeclarationTest
import de.bornholdtlee.baseproject.kotlinexamples.delegate.DelegateTest
import de.bornholdtlee.baseproject.kotlinexamples.extending.ExtendingTest
import de.bornholdtlee.baseproject.utils.Logger

class TestController(application: BaseApplication) : BaseController(application) {

    companion object {
        val TEST_DECLARATION = 0
        val TEST_SUBCLASS = TEST_DECLARATION + 1
        val TEST_CASTING = TEST_SUBCLASS + 1
        val TEST_LIST = TEST_CASTING + 1
        val TEST_LOOPINGS = TEST_LIST + 1
        val TEST_BREAK_IT = TEST_LOOPINGS + 1
        val TEST_IS_IN_WHEN = TEST_BREAK_IT + 1
        val TEST_DELEGATE = TEST_IS_IN_WHEN + 1
        val TEST_DATACLASS = TEST_DELEGATE + 1

        val MAX_TEST = TEST_DATACLASS

        const val BREAK_STRING = "breakIt"

    }

    var index: Int = 0

    fun getTestName(): String {
        return when (index) {
            TEST_IS_IN_WHEN -> "When"
            TEST_LOOPINGS -> "Loopings"
            TEST_BREAK_IT -> "Break it"
            TEST_SUBCLASS -> "Extending"
            TEST_DELEGATE -> "Delegate"
            TEST_DECLARATION -> "Declaration"
            TEST_DATACLASS -> "Dataclass"
            TEST_LIST -> "List"
            TEST_CASTING -> "Casting"
            else -> ""
        }
    }

    fun doIt() {
        Logger.error(getTestName())
        when (index) {
            TEST_DECLARATION -> DeclarationTest.doDeclarationTest()
            TEST_CASTING -> DeclarationTest.doCastingTest()
            TEST_SUBCLASS -> ExtendingTest.doExtendingTest()
            TEST_LIST -> ControlTest.doHandleList()
            TEST_LOOPINGS -> ControlTest.doLooping()
            TEST_BREAK_IT -> ControlTest.doBreakit()
            TEST_IS_IN_WHEN -> ControlTest.doIsWhen()
            TEST_DELEGATE -> DelegateTest.doDelegateTest()
            TEST_DATACLASS -> DataClassTest.doDataClassTest()
        }
    }

    fun switchToPrevious() {
        index--
        if (index < 0) index = 0
    }

    fun switchToNext() {
        index++
        if (index > MAX_TEST) index = MAX_TEST
    }

}