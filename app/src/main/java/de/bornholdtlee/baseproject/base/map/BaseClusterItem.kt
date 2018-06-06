package de.bornholdtlee.baseproject.base.map

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import de.bornholdtlee.baseproject.NULL_INTEGER
import de.bornholdtlee.baseproject.R
import java.io.Serializable

open class BaseClusterItem(
        private val location: LatLng,
        private val dataId: Int = NULL_INTEGER,
        private val markerTitle: String = "",
        private val description: String = "",
        open val icon: Int = R.drawable.marker_poi)
    : ClusterItem, Serializable {
//    constructor(location: LatLng,
//                markerTitle: String = "",
//                description: String = "",
//                icon: Int = R.drawable.marker_poi) : this(NULL_INTEGER, location, markerTitle, description, icon)

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
