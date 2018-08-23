package de.bornholdtlee.baseproject.base.map

import com.google.android.gms.maps.model.LatLng

interface MapListener {

    fun getMaxZoomLevel() : Float
    fun zoomToBorder(positions: ArrayList<LatLng>)

}
