package de.bornholdtlee.baseproject.ui.map

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.google.android.gms.maps.model.LatLng
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.TAB_MAP
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.base.map.BaseClusterItem
import de.bornholdtlee.baseproject.base.map.BaseClusterRenderer
import de.bornholdtlee.baseproject.base.map.BaseInfoViewAdapter
import de.bornholdtlee.baseproject.base.map.MapBaseFragment
import de.bornholdtlee.baseproject.base.navigation.NavigationBaseTab
import de.bornholdtlee.baseproject.model.Lesson
import de.bornholdtlee.baseproject.ui.creation.CreateLessonActivity
import de.bornholdtlee.baseproject.ui.map.clusteritems.LessonClusterItem
import de.bornholdtlee.baseproject.ui.map.clusteritems.PoiClusterItem
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

    override val renderer: BaseClusterRenderer
        get() = BaseClusterRenderer(context!!, googleMap, clusterManager)

    override val infoViewAdapter: BaseInfoViewAdapter by lazy {
        object : BaseInfoViewAdapter(renderer, LayoutInflater.from(context)) {
            override val layoutId: Int = R.layout.info_window_layout

            override fun initViews(windowView: View, clusterItem: BaseClusterItem) {
                val title = windowView.findViewById(R.id.title) as TextView
                title.text = clusterItem.title
                val image = windowView.findViewById(R.id.image) as ImageView
                image.setImageResource(clusterItem.icon)
            }
        }
    }

    override fun createPresenter(application: BaseApplication): MapPresenter {
        return MapPresenter(application, this)
    }

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
//        zoomCamera(-34.0, 151.0, 12f)
        addCircle(LatLng(-44.0, 151.0))
        addPolyline(-34.0, 151.0, -54.0, 181.0)
        addPolygone()
        activateModeNormal()

    }

    override fun renderMap(lessons: List<Lesson>, pois: List<LatLng>) {
        Logger.error("RENDER MAP: " + this.toString())
        clusterManager.clearItems()
        for (lesson in lessons) {
            clusterManager.addItem(LessonClusterItem(lesson))
        }

        for (poi in pois) {
            clusterManager.addItem(PoiClusterItem(poi))
        }

        renderNew()
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