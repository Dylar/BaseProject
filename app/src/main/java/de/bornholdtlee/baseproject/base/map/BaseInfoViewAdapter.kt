package de.bornholdtlee.baseproject.base.map

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import de.bornholdtlee.baseproject.R


class BaseInfoViewAdapter(private val inflater: LayoutInflater) : GoogleMap.InfoWindowAdapter {

    override fun getInfoWindow(marker: Marker): View {
        val popup = inflater.inflate(R.layout.info_window_layout, null)

        (popup.findViewById(R.id.title) as TextView).text = marker.snippet

        return popup
        //return null;
    }

    override fun getInfoContents(marker: Marker): View {
        val popup = inflater.inflate(R.layout.info_window_layout, null)

        (popup.findViewById(R.id.title) as TextView).text = marker.snippet

        return popup
    }

//    https://medium.com/@tonyshkurenko/work-with-clustermanager-bdf3d70fb0fd
}