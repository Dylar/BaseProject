package de.bornholdtlee.defaultproject.model.converter

import android.text.TextUtils
import io.objectbox.converter.PropertyConverter
import java.util.*

class IntegerListConverter : PropertyConverter<List<Int>, String> {

    companion object {
        private const val SEPARATOR = ';'
    }

    override fun convertToEntityProperty(databaseValue: String): List<Int> {
        val split = databaseValue.split(SEPARATOR).dropLastWhile { it.isEmpty() }.toTypedArray()
        val result = ArrayList<Int>()
        for (aSplit in split) {
            result.add(Integer.valueOf(aSplit))
        }
        return result
    }

    override fun convertToDatabaseValue(integers: List<Int>): String {
        var result = ""
        for (integer in integers) {
            if (!TextUtils.isEmpty(result)) {
                result += SEPARATOR
            }
            result += integer
        }
        return result
    }

}
