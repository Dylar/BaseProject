package de.bornholdtlee.baseproject.ui.map

import de.bornholdtlee.baseproject.base.IBaseView
import de.bornholdtlee.baseproject.model.Lesson
import de.bornholdtlee.baseproject.model.Poi

interface IMapView : IBaseView {
    fun renderMap(lessons: List<Lesson>, pois: List<Poi>)

}
