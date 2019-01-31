package de.bornholdtlee.baseproject.database.converter

import androidx.room.TypeConverter
import com.google.android.gms.maps.model.LatLng

class LocationConverter {
    @TypeConverter
    fun toDate(value: String?): LatLng? {
        val location = value?.split(";")
        return if (location == null) null else LatLng(location[0].toDouble(), location[1].toDouble())
    }

    @TypeConverter
    fun toLong(value: LatLng?): String? {
        return value?.let { "${it.latitude};${it.longitude}" }
    }
}
