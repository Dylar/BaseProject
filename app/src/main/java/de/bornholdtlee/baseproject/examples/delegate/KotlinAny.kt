package de.bornholdtlee.baseproject.examples.delegate

class KotlinAny
    : KotlinFunctions by object : KotlinFunctions {}
        , JavaFunctions by JavaFunctionsImpl() {
}