package de.bornholdtlee.baseproject.base.map

import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

abstract class BaseInfoViewAdapter(private val renderer: BaseClusterRenderer, private val inflater: LayoutInflater) : GoogleMap.InfoWindowAdapter {

    abstract val layoutId: Int

    override fun getInfoWindow(marker: Marker): View? {
        val popup = inflater.inflate(layoutId, null)
        initViews(popup, renderer.getClusterItem(marker))
        return popup
    }

    override fun getInfoContents(marker: Marker): View? {
//        val popup = inflater.inflate(R.layout.info_window_layout, null)
//        (popup.findViewById(R.id.title) as TextView).text = marker.snippet
        return null
    }

    abstract fun initViews(windowView: View, clusterItem: BaseClusterItem)

//    https://medium.com/@tonyshkurenko/work-with-clustermanager-bdf3d70fb0fd
}