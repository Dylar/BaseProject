package de.bornholdtlee.baseproject.ui.map

import com.google.android.gms.maps.model.LatLng
import de.bornholdtlee.baseproject.base.IBaseView
import de.bornholdtlee.baseproject.model.Lesson

interface IMapView : IBaseView {
    fun renderMap(lessons: List<Lesson>, pois: List<LatLng>)

}
