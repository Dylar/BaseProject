package de.bornholdtlee.baseproject.model.converter

import com.google.android.gms.maps.model.LatLng
import org.joda.time.DateTime

import io.objectbox.converter.PropertyConverter

class LatLngConverter : PropertyConverter<LatLng, String> {

    private val REGEX = ';'

    override fun convertToEntityProperty(databaseValue: String): LatLng {
        val split: List<String> = databaseValue.split(REGEX)
        return LatLng(split[0].toDouble(), split[1].toDouble())
    }

    override fun convertToDatabaseValue(entityProperty: LatLng): String {
        return entityProperty.latitude.toString() + REGEX + entityProperty.longitude
    }
}
