package de.bornholdtlee.baseproject.ui.map

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
import de.bornholdtlee.baseproject.ui.map.clusteritems.LessonClusterItem
import de.bornholdtlee.baseproject.ui.map.clusteritems.PoiClusterItem

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

    override val renderer: BaseClusterRenderer by lazy {
        BaseClusterRenderer(context!!, googleMap, clusterManager)
    }

    override val infoViewAdapter: BaseInfoViewAdapter? by lazy {
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

    val hamburg = LatLng(53.565278, 10.001389)
    val berlin = LatLng(52.516667, 13.388889)
    val sangerhausen = LatLng(51.466667, 11.3)
    val erfurt = LatLng(50.983333, 11.033333)
    val kiel = LatLng(54.333333, 10.133333)
    val koeln = LatLng(50.938056, 6.956944)
    val wien = LatLng(48.208333, 16.373056)

    override fun initMapItems() {
        presenter.initMap()
        addMarker(-34.0, 151.0, "SEATTLE", "Marker Description")
//        zoomCamera(-34.0, 151.0, 12f)
        addCircle(LatLng(-44.0, 151.0))
        addPolyline(-34.0, 151.0, -54.0, 181.0)
        addPolygone()
        activateModeNormal()

        clusterManager.addItem(PoiClusterItem(hamburg, title = "HAMBURG", snippet = "Hamburg"))
        clusterManager.addItem(PoiClusterItem(berlin, title = "BERLIN", snippet = "Berlin"))
        clusterManager.addItem(PoiClusterItem(sangerhausen, title = "SANGERHAUSEN", snippet = "Sangerhausen"))
        clusterManager.addItem(PoiClusterItem(erfurt, title = "ERFURT", snippet = "Erfurt"))
        clusterManager.addItem(PoiClusterItem(kiel, title = "KIEL", snippet = "Kiel"))
        clusterManager.addItem(PoiClusterItem(koeln, title = "KÖLN", snippet = "Köln"))
        clusterManager.addItem(PoiClusterItem(wien, title = "WIEN", snippet = "Wien"))

    }

    override fun onCameraIdle() {
        super.onCameraIdle()
        btnContainer.visibility = VISIBLE
    }

    override fun onCameraMove() {
        btnContainer.visibility = GONE
    }

    override fun onMapLongClick(location: LatLng?) {
        presenter.onMapLongClicked(location)
    }

    override fun addLesson(lesson: Lesson) {
        clusterManager.addItem(LessonClusterItem(lesson))
        renderNew()
    }

}