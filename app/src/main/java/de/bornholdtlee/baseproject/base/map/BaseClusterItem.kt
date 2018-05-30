package de.bornholdtlee.baseproject.base.map

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class BaseClusterItem(val location: LatLng, val markerTitle: String = "", val description: String = "") : ClusterItem {

    override fun getSnippet(): String {
        return description
    }

    override fun getTitle(): String {
        return markerTitle
    }

    override fun getPosition(): LatLng {
        return location
    }
}
