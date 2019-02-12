package de.bornholdtlee.baseproject.ui.map

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import butterknife.BindView
import butterknife.OnClick
import com.google.android.gms.maps.model.LatLng
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.TAB_MAP
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.base.map.BaseMapItemComponents
import de.bornholdtlee.baseproject.base.map.MapBaseFragment
import de.bornholdtlee.baseproject.base.navigation.NavigationBaseTab
import de.bornholdtlee.baseproject.injection.components.AppComponent
import de.bornholdtlee.baseproject.model.Lesson
import de.bornholdtlee.baseproject.model.Poi
import de.bornholdtlee.baseproject.ui.creation.CreateLessonActivity
import de.bornholdtlee.baseproject.ui.map.components.lesson.LessonItemComponents
import de.bornholdtlee.baseproject.ui.map.components.poi.PoiItemComponents
import de.bornholdtlee.baseproject.utils.Logger

class MapFragment : MapBaseFragment<IMapView, MapPresenter>(), IMapView, NavigationBaseTab {

    companion object {
        fun createInstance(): MapFragment {
            return MapFragment()
        }
    }

    @BindView(R.id.fragment_map_btn_container)
    lateinit var btnContainer: View

    override val mapViewId: Int = R.id.map_view

    override val layoutId: Int = R.layout.fragment_map

    override val navigationPosition: Int = TAB_MAP

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun createPresenter(application: BaseApplication): MapPresenter = MapPresenter(application, this)

    @OnClick(R.id.fragment_map_btn1)
    internal fun onBtn1() {
        activateModeNormal()
    }

    @OnClick(R.id.fragment_map_btn2)
    internal fun onBtn2() {
        activateModeHybrid()
    }

    @OnClick(R.id.fragment_map_btn3)
    internal fun onBtn3() {
        activateModeSatellite()
    }

    @OnClick(R.id.fragment_map_btn4)
    internal fun onBtn4() {
        activateModeTerrain()
    }

    override fun initMapItems() {
        presenter.initMap()
        addMarker(-34.0, 151.0, "SEATTLE", "Marker Description")
        zoomCamera(LatLng(53.565278, 10.001389), 10f)
        addCircle(LatLng(-44.0, 151.0))
        addPolyline(-34.0, 151.0, -54.0, 181.0)
        addPolygone()
        activateModeNormal()

    }

    override fun addItemComponents(components: MutableMap<String, BaseMapItemComponents<*, *, *>>) {
        components["pois"] = PoiItemComponents(context!!, this)
        components["lesson"] = LessonItemComponents(context!!, this)
    }

    override fun renderMap(lessons: List<Lesson>, pois: List<Poi>) {
        Logger.error("RENDER MAP: " + this.toString())
        renderMap("lesson", lessons)
        renderMap("pois", pois)
    }

    override fun onMapClick(location: LatLng?) {
//        Logger.error("KLCIK ON MAP fragment: " + toString())
//        Logger.error("KLCIK ON MAP clusterManager: " + clusterManager.toString())
//        Logger.error("KLCIK ON MAP mapView: " + mapView.toString())
//        Logger.error("KLCIK ON MAP googleMap: " + googleMap.toString())
//        clusterManager.clearItems()
//        clusterManager.addItem(PoiClusterItem(location!!))
//        renderNew()
        addMarker(location!!, "", "")
    }

    override fun onCameraIdle() {
        super.onCameraIdle()
        btnContainer.visibility = VISIBLE
    }

    override fun onCameraMove() {
        btnContainer.visibility = GONE
    }

    override fun onMapLongClick(location: LatLng?) {
        CreateLessonActivity.startActivity(baseActivity!!, location!!)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == CreateLessonActivity.REQUEST_CODE) {
            presenter.renderMap()
        }
    }

}