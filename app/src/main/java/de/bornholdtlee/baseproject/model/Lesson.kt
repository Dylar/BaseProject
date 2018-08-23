package de.bornholdtlee.baseproject.model

import com.google.android.gms.maps.model.LatLng
import de.bornholdtlee.baseproject.ui.map.components.MapItemInfo
import org.joda.time.DateTime

class Lesson(override var id: Long,
             override var position: LatLng,
             override var name: String,
             override var description: String) : MapItemInfo {

    var image: String = ""

    lateinit var startDate: DateTime
    lateinit var createdAt: DateTime
    lateinit var updatedAt: DateTime
    var deletedAt: DateTime? = null

    var organizer: MutableList<Organizer> = ArrayList()
    var attendees: MutableList<Attendee> = ArrayList()

}