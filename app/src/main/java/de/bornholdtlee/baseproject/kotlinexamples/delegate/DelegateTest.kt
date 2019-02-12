package de.bornholdtlee.baseproject.kotlinexamples.delegate

object DelegateTest {

    fun doDelegateTest() {
        val kotlinSubclass = KotlinAny(object : KotlinFunctions {})
        val javaSubclass = JavaObject(object : KotlinFunctions {})

        doSimpleStuff(kotlinSubclass)
        doMoreStuff(kotlinSubclass)
        doSimpleStuff(javaSubclass)
        doMoreStuff(javaSubclass)
    }

    private fun doMoreStuff(moreStuff: JavaFunctions) {
        moreStuff.doStuff()
        moreStuff.doMoreStuff()
    }

    private fun doSimpleStuff(functionClass: KotlinFunctions) {
        functionClass.printStuff()
        functionClass.doStuff()
        functionClass.doNothing()
    }

}