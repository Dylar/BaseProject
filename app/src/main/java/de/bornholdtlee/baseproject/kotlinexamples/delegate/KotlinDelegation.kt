package de.bornholdtlee.baseproject.kotlinexamples.delegate


class KotlinDelegation(kotlinFunctions: KotlinFunctions)
    : OpenClass("KotlinDelegation"),
        KotlinDuplicateFunctions by object : KotlinDuplicateFunctions {},
        KotlinFunctions by kotlinFunctions,
        JavaFunctions by JavaFunctionsImpl() {

    override fun doStuff() {
        super<KotlinFunctions>.doStuff()
        super<KotlinDuplicateFunctions>.doStuff()
    }
}