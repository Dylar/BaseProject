package de.bornholdtlee.baseproject.base.map

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import de.bornholdtlee.baseproject.ui.map.components.MapItemInfo
import java.io.Serializable

abstract class BaseClusterItem(
        val info: MapItemInfo)
    : ClusterItem, Serializable {

    abstract val icon: Int
    abstract val dataType: String

    override fun getSnippet(): String {
        return info.description
    }

    override fun getTitle(): String {
        return info.name
    }

    override fun getPosition(): LatLng {
        return info.location!!
    }

}
