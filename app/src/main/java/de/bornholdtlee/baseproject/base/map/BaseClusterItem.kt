package de.bornholdtlee.baseproject.base.map

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import de.bornholdtlee.baseproject.NULL_INTEGER
import java.io.Serializable

abstract class BaseClusterItem(
        private val position: LatLng,
        val dataId: Int = NULL_INTEGER,
        private val title: String = "",
        private val snippet: String = "")
    : ClusterItem, Serializable {

    abstract val icon: Int
    abstract val dataType: String

    override fun getSnippet(): String {
        return snippet
    }

    override fun getTitle(): String {
        return title
    }

    override fun getPosition(): LatLng {
        return position
    }
}
