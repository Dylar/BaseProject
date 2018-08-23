package de.bornholdtlee.baseproject.model

import com.google.android.gms.maps.model.LatLng
import de.bornholdtlee.baseproject.ui.map.components.MapItemInfo

class Poi(override var id: Long,
          override var position: LatLng,
          override var name: String,
          override var description: String) : MapItemInfo {
}