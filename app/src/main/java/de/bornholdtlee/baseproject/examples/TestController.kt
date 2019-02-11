package de.bornholdtlee.baseproject.examples

import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.controller.BaseController
import de.bornholdtlee.baseproject.examples.control.ControlTest
import de.bornholdtlee.baseproject.examples.declaration.DeclarationTest
import de.bornholdtlee.baseproject.examples.delegate.DelegateTest
import de.bornholdtlee.baseproject.examples.extending.ExtendingTest
import de.bornholdtlee.baseproject.utils.Logger

class TestController(application: BaseApplication) : BaseController(application) {

    companion object {
        val TEST_IS_IN_WHEN = 0
        val TEST_LOOPINGS = TEST_IS_IN_WHEN + 1
        val TEST_BREAK_IT = TEST_LOOPINGS + 1
        val TEST_SUBCLASS = TEST_BREAK_IT + 1
        val TEST_DELEGATE = TEST_SUBCLASS + 1
        val TEST_DECLARATION = TEST_DELEGATE + 1

        val MAX_TEST = TEST_DELEGATE

        const val BREAK_STRING = "breakIt"

    }

    fun getTestName(index: Int): String {
        return "Test: ${when (index) {
            TEST_IS_IN_WHEN -> "Is in when"
            TEST_LOOPINGS -> "Loopings"
            TEST_BREAK_IT -> "Break it"
            TEST_SUBCLASS -> "Subclass"
            TEST_DELEGATE -> "Delegate"
            TEST_DECLARATION -> "Declaration"
            else -> ""
        }}"
    }

    fun doIt(counter: Int) {
        Logger.info("TEST: ${getTestName(counter)}")
        when (counter) {
            TEST_IS_IN_WHEN -> ControlTest.doIsWhen()
            TEST_LOOPINGS -> ControlTest.doLooping()
            TEST_BREAK_IT -> ControlTest.doBreakit()
            TEST_SUBCLASS -> ExtendingTest.doExtendingTest()
            TEST_DELEGATE -> DelegateTest.doDelegateTest()
            TEST_DECLARATION -> DeclarationTest.doDeclarationTest()
        }
    }

}