package de.bornholdtlee.baseproject.kotlinexamples.dataclass

class NoDataClass() {

    var name: String = "NoName"
    var lastName: String = "NoLastName"
    var age: Int = 12
    var weight: Float = 70f

    operator fun component1(): String {
        return name
    }

    operator fun component2(): String {
        return lastName
    }

    operator fun component3(): Int {
        return age
    }

}