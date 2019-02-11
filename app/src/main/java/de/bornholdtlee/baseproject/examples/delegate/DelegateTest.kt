package de.bornholdtlee.baseproject.examples.delegate

object DelegateTest {

    fun doDelegateTest() {
        val kotlinSubclass = KotlinAny()
        val javaSubclass = JavaObject()

        doSimpleStuff(kotlinSubclass)
        doMoreStuff(javaSubclass)
    }

    private fun doMoreStuff(moreStuff: JavaFunctions) {
        moreStuff.moreStuff()
        moreStuff.doMoreStuff()
    }

    private fun doSimpleStuff(functionClass: KotlinFunctions) {
        functionClass.printStuff()
        functionClass.doStuff()
        functionClass.doNothing()
    }

}