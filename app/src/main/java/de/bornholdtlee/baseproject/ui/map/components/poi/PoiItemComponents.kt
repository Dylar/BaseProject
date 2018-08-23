package de.bornholdtlee.baseproject.ui.map.components.poi

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.clustering.ClusterManager
import de.bornholdtlee.baseproject.base.map.BaseMapItemComponents
import de.bornholdtlee.baseproject.base.map.MapListener
import de.bornholdtlee.baseproject.ui.map.components.MapItemInfo

class PoiItemComponents(context: Context, mapListener: MapListener) : BaseMapItemComponents<PoiClusterItem, PoiClusterRenderer, PoiClusterInfoAdapter>(context, mapListener) {
    override fun createClusterItem(item: MapItemInfo): PoiClusterItem {
        return PoiClusterItem(item)
    }

    override fun createInfoViewAdapter(renderer: PoiClusterRenderer): PoiClusterInfoAdapter {
        return PoiClusterInfoAdapter(renderer)
    }

    override fun createRenderer(context: Context, googleMap: GoogleMap, clusterManager: ClusterManager<PoiClusterItem>)
            : PoiClusterRenderer = PoiClusterRenderer(context, googleMap, clusterManager)

}