package de.bornholdtlee.baseproject.kotlinexamples.extending

import de.bornholdtlee.baseproject.utils.Logger

object ExtendingTest {

    fun doExtendingTest() {
        val kotlinSubclass = KotlinSubclass()
        val kotlinNamedSubclass = KotlinSubclass("Kotlin named")
        val javaSubclass: BaseClass = JavaSubclass()
        val javaNamedSubclass: BaseClass = JavaSubclass("Java named")

        print(kotlinSubclass)
        print(javaSubclass)
        print(kotlinNamedSubclass)
        print(javaNamedSubclass)
    }

    private fun print(baseClass: BaseClass) {
        Logger.info("Name: ${baseClass.name}")
        baseClass.openMethod()
        baseClass.finalMethod()
    }

}