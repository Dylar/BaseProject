package de.bornholdtlee.baseproject.ui.map

import com.google.android.gms.maps.model.LatLng
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.base.BasePresenter
import de.bornholdtlee.baseproject.controller.LessonController
import de.bornholdtlee.baseproject.controller.OrganizerController
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.injection.components.AppComponent
import de.bornholdtlee.baseproject.model.Lesson
import javax.inject.Inject

class MapPresenter(application: BaseApplication, view: IMapView) : BasePresenter<IMapView>(application, view), IInjection {

    val hamburg = LatLng(53.565278, 10.001389)
    val berlin = LatLng(52.516667, 13.388889)
    val sangerhausen = LatLng(51.466667, 11.3)
    val erfurt = LatLng(50.983333, 11.033333)
    val kiel = LatLng(54.333333, 10.133333)
    val koeln = LatLng(50.938056, 6.956944)
    val wien = LatLng(48.208333, 16.373056)

    @Inject
    lateinit var lessonController: LessonController

    @Inject
    lateinit var organizerController: OrganizerController

    var lessons: List<Lesson> = ArrayList()
    var pois: List<LatLng> = listOf(hamburg, berlin, sangerhausen, erfurt, kiel, koeln, wien)

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    fun initMap() {
        renderMap()
    }

    fun renderMap() {
        lessons = lessonController.lessonRepository.getAll()
        getView().renderMap(lessons, pois)
    }

}
