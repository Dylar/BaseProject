package de.bornholdtlee.baseproject.base.map

import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

abstract class BaseClusterInfoAdapter(private val renderer: BaseClusterRenderer) : GoogleMap.InfoWindowAdapter {

    private val inflater: LayoutInflater = LayoutInflater.from(renderer.context)

    abstract val layoutId: Int

    override fun getInfoWindow(marker: Marker): View? {
        val popup = inflater.inflate(layoutId, null)
        initViews(popup, renderer.getClusterItem(marker))
        return popup
    }

    override fun getInfoContents(marker: Marker): View? {
        return null
    }

    abstract fun initViews(windowView: View, clusterItem: BaseClusterItem)

}