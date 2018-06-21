package de.bornholdtlee.baseproject.model

import com.google.android.gms.maps.model.LatLng
import de.bornholdtlee.baseproject.NULL_INTEGER
import de.bornholdtlee.baseproject.model.converter.DateTimeConverter
import de.bornholdtlee.baseproject.model.converter.LatLngConverter
import io.objectbox.annotation.Convert
import org.joda.time.DateTime

class Lesson {

    var id: Long = NULL_INTEGER.toLong()

    lateinit var location: LatLng

    var name: String = ""
    var description: String = ""
    var image: String = ""

    lateinit var startDate: DateTime
    lateinit var createdAt: DateTime
    lateinit var updatedAt: DateTime
    var deletedAt: DateTime? = null

    var organizer: MutableList<Organizer> = ArrayList()
    var attendees: MutableList<Attendee> = ArrayList()

}