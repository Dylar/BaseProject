package de.bornholdtlee.baseproject.examples.extending

import de.bornholdtlee.baseproject.utils.Logger

object ExtendingTest {

    fun doExtendingTest() {
        val kotlinSubclass = KotlinSubclass()
        val kotlinNamedSubclass = KotlinSubclass("Kotlin named")
        val javaSubclass: BaseClass = JavaSubclass()
        val javaNamedSubclass: BaseClass = JavaSubclass("Java named")

        print(kotlinSubclass)
        print(kotlinNamedSubclass)
        print(javaSubclass)
        print(javaNamedSubclass)
    }

    private fun print(baseClass: BaseClass) {
        Logger.info("Name: ${baseClass.name}")
        baseClass.openMethod()
        baseClass.sealedMethod()
    }

}