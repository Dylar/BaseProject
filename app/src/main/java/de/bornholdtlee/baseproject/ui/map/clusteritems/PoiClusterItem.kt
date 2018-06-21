package de.bornholdtlee.baseproject.ui.map.clusteritems

import com.google.android.gms.maps.model.LatLng
import de.bornholdtlee.baseproject.CLUSTER_TYPE_POI
import de.bornholdtlee.baseproject.NULL_INTEGER
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.base.map.BaseClusterItem

class PoiClusterItem(
        position: LatLng,
        dataId: Int = NULL_INTEGER,
        title: String = "",
        snippet: String = "")
    : BaseClusterItem(position, dataId, title, snippet) {

    override val dataType: String = CLUSTER_TYPE_POI
    override val icon: Int = R.drawable.marker_poi

//    override val icon: Int = lesson.organizer.get(0).image
}