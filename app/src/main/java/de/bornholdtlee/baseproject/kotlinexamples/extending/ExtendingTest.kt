package de.bornholdtlee.baseproject.kotlinexamples.extending

import de.bornholdtlee.baseproject.utils.Logger

object ExtendingTest {

    fun doExtendingTest() {
        Logger.error("Init classes")
        val kotlinSubclass = KotlinSubclass()
        val kotlinNamedSubclass = KotlinSubclass("Kotlin named")
        val javaSubclass: BaseClass = JavaSubclass()
        val javaNamedSubclass: BaseClass = JavaSubclass("Java named")

        Logger.error("Use unnamed class")
        useMethods(kotlinSubclass)
        useMethods(javaSubclass)

        Logger.error("Use named class")
        useMethods(kotlinNamedSubclass)
        useMethods(javaNamedSubclass)
    }

    private fun useMethods(baseClass: BaseClass) {
        Logger.info("Name: ${baseClass.name}")
        baseClass.openMethod()
        Logger.info("Final method succeeded: ${baseClass.finalMethod()}")
    }

}