package de.bornholdtlee.baseproject.kotlinexamples.dataclass

import de.bornholdtlee.baseproject.utils.Logger

object DataClassTest {

    fun doDataClassTest() {
        val personData1 = PersonDataClass("Peter", "Lustig", 42)
        val personData2 = personData1.copy(age = 24)

        Logger.info("Data1: $personData1")
        Logger.info("Data2: $personData2")
        Logger.info("Both equals: ${personData1 == personData2}")

        val personData3: PersonDataClass = personData1.copy()
        personData1.weight = 100f
        Logger.info("Still both equals: ${personData1 == personData3}")
        Logger.info("Not identical: ${personData1 === personData3}")

        val (_, lastName, age) = getPersonData()
        Logger.info("LastName: $lastName, Age: $age")

        testNoDataClass()
        loopMap()
    }

    private fun testNoDataClass() {
        val noDataClass = NoDataClass()
        val (name, _, age) = noDataClass
        Logger.info("NoDataClass: Name: $name, Age: $age")
    }

    fun getPersonData(): PersonDataClass {
        return PersonDataClass("Name", "LastName", 0)
    }

    fun loopMap() {
        val map = HashMap<String, String>()
        map["1"] = "Data1"
        map["2"] = "Data2"
        map["3"] = "Data3"
        for ((key, value) in map) {
            Logger.info("Key: $key, Value: $value")
        }
    }
}