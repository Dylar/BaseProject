package de.bornholdtlee.baseproject.base.map

import android.content.Context
import android.graphics.Bitmap
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


abstract class BaseClusterRenderer(val context: Context,
                                   map: GoogleMap,
                                   clusterManager: ClusterManager<BaseClusterItem>)
    : DefaultClusterRenderer<BaseClusterItem>(context, map, clusterManager) {

    abstract val backgroundId: Int //R.drawable.background_circle
    abstract val textStyleId: Int //R.style.AppTheme_WhiteTextAppearance

    var clusterIconGenerator: IconGenerator = IconGenerator(context)

    override fun shouldRenderAsCluster(cluster: Cluster<BaseClusterItem>?): Boolean {
        return cluster!!.items.size > 1
    }

    override fun onBeforeClusterItemRendered(item: BaseClusterItem?, markerOptions: MarkerOptions?) {
        val markerDescriptor: BitmapDescriptor = BitmapDescriptorFactory.fromResource(item!!.icon)
        markerOptions!!.icon(markerDescriptor).snippet(item.snippet)
    }

    override fun onBeforeClusterRendered(cluster: Cluster<BaseClusterItem>?, markerOptions: MarkerOptions?) {
        clusterIconGenerator.setBackground(ContextCompat.getDrawable(context, backgroundId))
        clusterIconGenerator.setTextAppearance(textStyleId)
        val icon: Bitmap = clusterIconGenerator.makeIcon(cluster!!.size.toString())
        markerOptions!!.icon(BitmapDescriptorFactory.fromBitmap(icon))
    }

}