package de.bornholdtlee.baseproject.model

import com.google.android.gms.maps.model.LatLng
import de.bornholdtlee.baseproject.NULL_INTEGER
import de.bornholdtlee.baseproject.model.converter.DateTimeConverter
import de.bornholdtlee.baseproject.model.converter.LatLngConverter
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import org.joda.time.DateTime

class Lesson {

    var id: Long = NULL_INTEGER.toLong()

    @Convert(converter = LatLngConverter::class, dbType = String::class)
    lateinit var location: LatLng

    lateinit var name: String
    var description: String = ""
    var image: String = ""

    @Convert(converter = DateTimeConverter::class, dbType = Long::class)
    lateinit var startDate: DateTime
    @Convert(converter = DateTimeConverter::class, dbType = Long::class)
    lateinit var createdAt: DateTime
    @Convert(converter = DateTimeConverter::class, dbType = Long::class)
    lateinit var updatedAt: DateTime
    @Convert(converter = DateTimeConverter::class, dbType = Long::class)
    var deletedAt: DateTime? = null

    lateinit var organizer: MutableList<Organizer>
    lateinit var attendees: MutableList<Attendee>

}