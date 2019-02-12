package de.bornholdtlee.baseproject.kotlinexamples.delegate

class KotlinAny(kotlinFunctions: KotlinFunctions)
    : KotlinDuplicateFunctions by object : KotlinDuplicateFunctions {},
        KotlinFunctions by kotlinFunctions,
        JavaFunctions by JavaFunctionsImpl() {

    override fun doStuff() {
        super<KotlinFunctions>.doStuff()
        super<KotlinDuplicateFunctions>.doStuff()
    }
}