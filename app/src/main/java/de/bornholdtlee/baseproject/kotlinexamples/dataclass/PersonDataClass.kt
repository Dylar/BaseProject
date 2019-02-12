package de.bornholdtlee.baseproject.kotlinexamples.dataclass

data class PersonDataClass(var name: String,
                           var lastName: String,
                           var age: Int) {

    var weight: Float = 70f

    // equals()/HasCode()
    // toString()
    // copy()
    //componentN()
}