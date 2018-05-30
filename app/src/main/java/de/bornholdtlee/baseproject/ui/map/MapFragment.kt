package de.bornholdtlee.baseproject.ui.map

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import butterknife.BindView
import butterknife.OnClick
import com.google.android.gms.maps.model.LatLng
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.TAB_MAP
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.base.map.MapBaseFragment
import de.bornholdtlee.baseproject.base.navigation.NavigationBaseTab
import de.bornholdtlee.baseproject.injection.IBind
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.injection.components.AppComponent

class MapFragment : MapBaseFragment<IMapView, MapPresenter>(), IMapView, IInjection, NavigationBaseTab, IBind {

    companion object {
        fun createInstance(): MapFragment {
            return MapFragment()
        }
    }

    @BindView(R.id.fragment_map_btn_container)
    lateinit var btnContainer : View

    override val mapViewId: Int = R.id.map_view

    override val layoutId: Int = R.layout.fragment_map

    override val navigationPosition: Int = TAB_MAP

    override fun inject(appComponent: AppComponent?) {
        appComponent?.inject(this)
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

    override fun initMap() {
        addMarker(berlin, "Berlin", "Dreckig")
        addMarker(-34.0, 151.0, "Marker Title", "Marker Description")
        addMarker(hamburg, "Hamburg", "Die Perle")
        zoomCamera(-34.0, 151.0, 12f)
        addCircle(LatLng(-44.0, 151.0))
        addPolyline(-34.0, 151.0, -54.0, 181.0)
        addPolygone()
        activateModeNormal()
    }

    override fun onCameraIdle() {
        btnContainer.visibility = VISIBLE
    }

    override fun onCameraMove() {
        btnContainer.visibility = GONE
    }
}