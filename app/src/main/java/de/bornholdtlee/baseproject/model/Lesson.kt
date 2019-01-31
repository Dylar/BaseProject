package de.bornholdtlee.baseproject.model

import com.google.android.gms.maps.model.LatLng
import de.bornholdtlee.baseproject.ui.map.components.MapItemInfo
import org.joda.time.DateTime

class Lesson(override var id: Long,
             override var location: LatLng?,
             override var name: String,
             override var description: String) : MapItemInfo {



}