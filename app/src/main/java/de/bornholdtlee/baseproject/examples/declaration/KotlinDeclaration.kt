package de.bornholdtlee.baseproject.examples.declaration

class KotlinDeclaration {

    var normalProperty: String = "A String"

    var integer = 0
        set(value) {
            field = value * 2
        }
        get() = field - 3

    var isSeven = false
        get() = integer == 7
        private set

    var nullValue: String? = null
    lateinit var lateProperty: String

    fun latePropertyIsInitialized(): Boolean {
        //Lateinit check only in class/subclass
        return ::lateProperty.isInitialized
    }
}