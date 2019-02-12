package de.bornholdtlee.baseproject.kotlinexamples.declaration

import de.bornholdtlee.baseproject.utils.Logger

class KotlinDeclaration {

    public var publicProperty: String = "A public property"
    internal var internalProperty: String = "A internal property"
    protected var protectedProperty: String = "A protected property"
    private var privateProperty: String = "A private property"

    var nullProperty: String? = null
    lateinit var lateProperty: String

    var integer = 0
        set(value) {
            field = value * 2
        }
        get() {
            Logger.info("Getter called always")
            return field - 3
        }

    var isSeven = false
        get() = integer == 7
        private set


    fun latePropertyIsInitialized(): Boolean {
        //Lateinit check only in class/subclass
        return ::lateProperty.isInitialized
    }

    val lazyProperty: String by lazy {
        Logger.info("Lazy called once")
        "Lazy Value"
    }

    val busyProperty: Int by BusyProperty()

    @JvmField
    var justAField: String = "A Field"

}
