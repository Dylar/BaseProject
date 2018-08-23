package de.bornholdtlee.baseproject.utils

import de.bornholdtlee.baseproject.base.BaseApplication
import java.util.*

class StringUtils(application: BaseApplication) : BaseUtils(application) {

    fun containsCaseInsensitive(string: String, contains: String): Boolean {
        val lowerString = string.toLowerCase(Locale.getDefault())
        val lowerContains = contains.toLowerCase(Locale.getDefault())
        return lowerString.contains(lowerContains)
    }

}