package de.bornholdtlee.baseproject.base.map

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

class BaseClusterRenderer(private val context: Context,
                          map: GoogleMap,
                          clusterManager: ClusterManager<BaseClusterItem>)
    : DefaultClusterRenderer<BaseClusterItem>(context, map, clusterManager) {

    override fun onBeforeClusterItemRendered(item: BaseClusterItem?, markerOptions: MarkerOptions?) {
    }
//    https://medium.com/@tonyshkurenko/work-with-clustermanager-bdf3d70fb0fd
}