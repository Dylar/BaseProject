package de.bornholdtlee.baseproject.kotlinexamples.delegate

object DelegateTest {

    fun doDelegateTest() {
        val kotlinSubclass = KotlinDelegation(object : KotlinFunctions {})
        val javaSubclass = JavaDelegation(object : KotlinFunctions {})

        doSimpleStuff(kotlinSubclass)
        doSimpleStuff(javaSubclass)

        doMoreStuff(kotlinSubclass)
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