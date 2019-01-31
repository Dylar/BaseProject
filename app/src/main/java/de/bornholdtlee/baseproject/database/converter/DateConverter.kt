package de.bornholdtlee.baseproject.database.converter

import androidx.room.TypeConverter
import org.joda.time.DateTime


class DateConverter {
    @TypeConverter
    fun toDate(value: Long?): DateTime? {
        return if (value == null) null else DateTime(value)
    }

    @TypeConverter
    fun toLong(value: DateTime?): Long? {
        return value?.millis
    }
}
