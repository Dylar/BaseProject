package de.bornholdtlee.baseproject.base.map

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.support.v4.content.ContextCompat
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator
import de.bornholdtlee.baseproject.R


class BaseClusterRenderer(val context: Context,
                          map: GoogleMap,
                          clusterManager: ClusterManager<BaseClusterItem>)
    : DefaultClusterRenderer<BaseClusterItem>(context, map, clusterManager) {

    val backgroundId: Int = R.drawable.background_circle_black
    val textStyleId: Int = R.style.AppTheme_WhiteTextAppearance

    var clusterIconGenerator = IconGenerator(context)

    override fun shouldRenderAsCluster(cluster: Cluster<BaseClusterItem>?): Boolean {
        return cluster!!.items.size > 1
    }

    override fun onBeforeClusterItemRendered(item: BaseClusterItem?, markerOptions: MarkerOptions?) {
//
//        val itemMapView = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.item_map_lesson, null)
//        val imageView = itemMapView.findViewById(R.id.image) as ImageView
//        imageView.setImageResource(item!!.icon)
//
//        val markerDescriptor: BitmapDescriptor = BitmapDescriptorFactory.fromBitmap(createDrawableFromView(context, itemMapView))
//        markerOptions!!.icon(markerDescriptor).snippet(item.snippet)

        val itemMapView = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.cluster_map_lesson, null)
        val imageView1 = itemMapView.findViewById(R.id.image1) as ImageView
        val imageView2 = itemMapView.findViewById(R.id.image2) as ImageView
        val imageView3 = itemMapView.findViewById(R.id.image3) as ImageView
        val imageView4 = itemMapView.findViewById(R.id.image4) as ImageView

        imageView1.setImageResource(item!!.icon)
        imageView2.setImageResource(item!!.icon)
        imageView3.setImageResource(item!!.icon)
        imageView4.setImageResource(item!!.icon)

        val markerDescriptor: BitmapDescriptor = BitmapDescriptorFactory.fromBitmap(createDrawableFromView(context, itemMapView))
        markerOptions!!.icon(markerDescriptor).snippet(item.snippet)
    }

    override fun onBeforeClusterRendered(cluster: Cluster<BaseClusterItem>?, markerOptions: MarkerOptions?) {
//        clusterIconGenerator.setBackground(ContextCompat.getDrawable(context, backgroundId))
//        clusterIconGenerator.setTextAppearance(textStyleId)
//        val icon: Bitmap = clusterIconGenerator.makeIcon(cluster!!.size.toString())
//        markerOptions!!.icon(BitmapDescriptorFactory.fromBitmap(icon))

        val itemMapView = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.cluster_map_lesson, null)
        val imageView1 = itemMapView.findViewById(R.id.image1) as ImageView
        val imageView2 = itemMapView.findViewById(R.id.image2) as ImageView
        val imageView3 = itemMapView.findViewById(R.id.image3) as ImageView
        val imageView4 = itemMapView.findViewById(R.id.image4) as ImageView

        val list = ArrayList<BaseClusterItem>()
        for (baseClusterItem in cluster!!.items.iterator()) {
            list.add(baseClusterItem)
        }

        if (list.size > 0) {
            imageView1.setImageResource(list[0].icon)
        }
        if (list.size > 1) {
            imageView2.setImageResource(list[1].icon)
        }
        if (list.size > 2) {
            imageView3.setImageResource(list[2].icon)
        }
        if (list.size > 3) {
            imageView4.setImageResource(list[3].icon)
        }

        val markerDescriptor: BitmapDescriptor = BitmapDescriptorFactory.fromBitmap(createDrawableFromView(context, itemMapView))
        markerOptions!!.icon(markerDescriptor)
    }

    fun createDrawableFromView(context: Context, view: View): Bitmap {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)

        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels)
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
        view.buildDrawingCache()
        val bitmap = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        view.draw(canvas)

        return bitmap
    }

}