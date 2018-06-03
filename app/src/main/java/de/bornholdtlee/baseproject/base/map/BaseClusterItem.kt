package de.bornholdtlee.baseproject.base.map

import de.bornholdtlee.baseproject.R
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

open class BaseClusterItem(private val location: LatLng, private val markerTitle: String = "", private val description: String = "", open val icon: Int = R.drawable.marker_poi) : ClusterItem {

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
