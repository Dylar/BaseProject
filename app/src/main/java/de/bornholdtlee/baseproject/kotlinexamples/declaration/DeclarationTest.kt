package de.bornholdtlee.baseproject.kotlinexamples.declaration

import de.bornholdtlee.baseproject.utils.Logger

object DeclarationTest {

    fun doDeclarationTest() {
        Logger.error("Test visibility")
        val declaration = KotlinDeclaration()

        Logger.info("Public: ${declaration.publicProperty}")
        Logger.info("Internal: ${declaration.internalProperty}")
//        Logger.info("Protected: ${declaration.protectedProperty}")
//        Logger.info("Private: ${declaration.privateProperty}")

        Logger.error("Set integer and check")
        declaration.integer = 7
        checkIsSeven(declaration)
        declaration.integer = 5
        checkIsSeven(declaration)

        Logger.error("Check null and set value")
        checkNullProperty(declaration)
        declaration.nullProperty = "Not Null"
        checkNullProperty(declaration)

        Logger.error("Use nullProperty")
        val newString = declaration.nullProperty!!.replace("Null", "");
        Logger.info("New string: $newString")

        Logger.error("Check lateProperty and set value")
        checkLateProperty(declaration)
        declaration.lateProperty = "Late init"
        checkLateProperty(declaration)

        Logger.error("Check lazyProperty twice")
        checkLazy(declaration)
        checkLazy(declaration)


    }

    private fun checkNullProperty(declaration: KotlinDeclaration) {
        Logger.error("Test nullProperty")
        var printString = "Is null"
        declaration.nullProperty?.let {
            printString = it.replace("Null", "")
        }
        Logger.info("NullValue: $printString")
    }

    private fun checkLateProperty(declaration: KotlinDeclaration) {
        Logger.error("Test lateProperty")
        if (declaration.latePropertyIsInitialized()) {
            Logger.info("LateProperty initialized: ${declaration.lateProperty}")
        } else {
            try {
                Logger.info("LateProperty initialized: ${declaration.lateProperty}")
            } catch (error: Exception) {
                Logger.info(error.message ?: "Error")
            }
        }
    }

    private fun checkIsSeven(declaration: KotlinDeclaration) {
        Logger.info("Integer isSeven: ${declaration.isSeven}")
    }

    private fun checkLazy(declaration: KotlinDeclaration) {
        Logger.error("Test Lazy + Busy")
        Logger.info("LazyProp: ${declaration.lazyProperty}")
        Logger.info("BusyProp: ${declaration.busyProperty}")
    }

    fun doCastingTest() {
        val string: Any = "A String"
        val int: Any = 0
        val theString: String? = string as String?
        val noString: String? = int as? String

        Logger.info("The String: $theString")
        Logger.info("No String: $noString")

        try {
            val castWrong = int as String
            Logger.info("Never reached: $castWrong")
        } catch (error: Exception) {
            Logger.info("Error: ${error.message ?: "Error"}")
        }

    }

}