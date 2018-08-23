package de.bornholdtlee.baseproject.ui.map.components.poi

import de.bornholdtlee.baseproject.CLUSTER_TYPE_POI
import de.bornholdtlee.baseproject.NULL_INTEGER
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.base.map.BaseClusterItem
import de.bornholdtlee.baseproject.ui.map.components.MapItemInfo

class PoiClusterItem(
        info: MapItemInfo)
    : BaseClusterItem(info) {

    override val dataType: String = CLUSTER_TYPE_POI
    override val icon: Int = R.drawable.marker_poi

//    override val icon: Int = lesson.organizer.get(0).image
}