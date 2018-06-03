package de.bornholdtlee.baseproject.ui.map

import de.bornholdtlee.baseproject.base.IBaseView
import de.bornholdtlee.baseproject.model.Lesson

interface IMapView : IBaseView {
    fun addLesson(lesson: Lesson)

}
