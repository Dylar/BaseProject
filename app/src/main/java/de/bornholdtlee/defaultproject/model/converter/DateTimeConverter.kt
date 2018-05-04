package de.bornholdtlee.defaultproject.model.converter

import org.joda.time.DateTime

import io.objectbox.converter.PropertyConverter

class DateTimeConverter : PropertyConverter<DateTime, Long> {

    override fun convertToEntityProperty(databaseValue: Long?): DateTime {
        return DateTime(databaseValue)
    }

    override fun convertToDatabaseValue(entityProperty: DateTime): Long? {
        return entityProperty.getMillis()
    }
}
