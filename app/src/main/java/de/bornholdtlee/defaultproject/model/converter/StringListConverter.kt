package de.bornholdtlee.defaultproject.model.converter

import android.text.TextUtils
import io.objectbox.converter.PropertyConverter
import java.util.*

class StringListConverter : PropertyConverter<List<String>, String> {

    companion object {
        private const val SEPARATOR = ';'
    }

    override fun convertToEntityProperty(databaseValue: String): List<String> {
        val split = databaseValue.split(SEPARATOR).dropLastWhile { it.isEmpty() }.toTypedArray()
        val result = ArrayList<String>()
        for (aSplit in split) {
            result.add(aSplit)
        }
        return result
    }

    override fun convertToDatabaseValue(strings: List<String>): String {
        var result = ""
        for (integer in strings) {
            if (!TextUtils.isEmpty(result)) {
                result += SEPARATOR
            }
            result += integer
        }
        return result
    }


}
