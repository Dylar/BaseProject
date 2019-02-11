package de.bornholdtlee.baseproject.examples.declaration

import de.bornholdtlee.baseproject.utils.Logger

object DeclarationTest {

    fun doDeclarationTest() {
        val declaration = KotlinDeclaration()
        declaration.integer = 7
        checkThings(declaration)
        declaration.nullValue = "Not Null"
        declaration.integer = 5
        declaration.lateProperty = "Late init"
        checkThings(declaration)
    }

    fun checkThings(declaration: KotlinDeclaration) {
        var printString = ""
        declaration.nullValue?.let {
            printString = it.replace("e", "")
        }

        Logger.info("NullValue not null: $printString")
        Logger.info("Integer isSeven: ${declaration.isSeven}")

        if ((declaration.latePropertyIsInitialized())) {
            Logger.info("LateProperty initialized: ${declaration.lateProperty}")
        } else {
            try {
                Logger.info("LateProperty initialized: ${declaration.lateProperty}")
            } catch (error: Exception) {
                Logger.info("LateProperty not initialized: ${error.message}")
            }
        }
    }
}