package de.bornholdtlee.baseproject.utils

import java.util.*

class StringUtils {

    fun containsCaseInsensitive(string: String, contains: String): Boolean {
        val lowerString = string.toLowerCase(Locale.getDefault())
        val lowerContains = contains.toLowerCase(Locale.getDefault())
        return lowerString.contains(lowerContains)
    }

}