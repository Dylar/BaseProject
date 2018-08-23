package de.bornholdtlee.baseproject.ui.map.components

import com.google.android.gms.maps.model.LatLng

interface MapItemInfo {
    var id: Long
    var position: LatLng
    var name: String
    var description: String
}