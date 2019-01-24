package de.bornholdtlee.baseproject.model.Test

class TesteMich(var basi: Basi) : Basi by basi {

    override fun method1(): String {
        return "NEIN WAS IS DAS"
    }

}