package de.bornholdtlee.baseproject.base.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v4.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator
import de.bornholdtlee.baseproject.R


class BaseClusterRenderer(private val context: Context,
                          map: GoogleMap,
                          clusterManager: ClusterManager<BaseClusterItem>)
    : DefaultClusterRenderer<BaseClusterItem>(context, map, clusterManager) {

    var clusterIconGenerator: IconGenerator = IconGenerator(context)

    override fun onBeforeClusterItemRendered(item: BaseClusterItem?, markerOptions: MarkerOptions?) {

//        val markerDescriptor: BitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
        val markerDescriptor: BitmapDescriptor = BitmapDescriptorFactory.fromResource(item!!.icon)
        markerOptions!!.icon(markerDescriptor).snippet(item.snippet)
    }

    override fun onBeforeClusterRendered(cluster: Cluster<BaseClusterItem>?, markerOptions: MarkerOptions?) {
        clusterIconGenerator.setBackground(ContextCompat.getDrawable(context, R.drawable.background_circle))
        clusterIconGenerator.setTextAppearance(R.style.AppTheme_WhiteTextAppearance)
        val icon: Bitmap = clusterIconGenerator.makeIcon(cluster!!.size.toString())
        markerOptions!!.icon(BitmapDescriptorFactory.fromBitmap(icon))
    }
//    https://medium.com/@tonyshkurenko/work-with-clustermanager-bdf3d70fb0fd

    override fun shouldRenderAsCluster(cluster: Cluster<BaseClusterItem>?): Boolean {
        return cluster!!.items.size > 1
    }
}