package de.bornholdtlee.baseproject.base.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer


abstract class BaseClusterRenderer<CI : BaseClusterItem>(val context: Context,
                                                         map: GoogleMap,
                                                         clusterManager: ClusterManager<CI>)
    : DefaultClusterRenderer<CI>(context, map, clusterManager) {

    abstract val itemLayoutId: Int
    abstract val clusterLayoutId: Int
    open val showClusterOnSize: Int = 1

    override fun shouldRenderAsCluster(cluster: Cluster<CI>?): Boolean {
        return cluster!!.items.size > showClusterOnSize
    }

    override fun onBeforeClusterItemRendered(item: CI?, markerOptions: MarkerOptions?) {
        val itemMapView = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(itemLayoutId, null)
        initItemView(itemMapView, item!!)
        val viewBitmap = getMarkerBitmapFromView(itemMapView)
        val markerDescriptor: BitmapDescriptor = BitmapDescriptorFactory.fromBitmap(viewBitmap)
        markerOptions!!.icon(markerDescriptor).snippet(item.snippet)
    }

    abstract fun initItemView(itemMapView: View, item: CI)

    override fun onBeforeClusterRendered(cluster: Cluster<CI>?, markerOptions: MarkerOptions?) {
        val clusterMapView = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(clusterLayoutId, null)
        initClusterView(clusterMapView, cluster!!.items)
        val viewBitmap = getMarkerBitmapFromView(clusterMapView)
        val markerDescriptor: BitmapDescriptor = BitmapDescriptorFactory.fromBitmap(viewBitmap)
        markerOptions!!.icon(markerDescriptor)
    }

    abstract fun initClusterView(clusterMapView: View, items: Collection<CI>)

    private fun getMarkerBitmapFromView(view: View): Bitmap {

        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        view.buildDrawingCache()
        val returnedBitmap = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight,
                Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN)
        val drawable = view.background
        drawable?.draw(canvas)
        view.draw(canvas)
        return returnedBitmap

    }

}