package de.bornholdtlee.defaultproject.ui.map

import de.bornholdtlee.defaultproject.R
import de.bornholdtlee.defaultproject.TAB_MAP
import de.bornholdtlee.defaultproject.base.BaseActivity
import de.bornholdtlee.defaultproject.base.BaseApplication
import de.bornholdtlee.defaultproject.base.MapBaseFragment
import de.bornholdtlee.defaultproject.base.NavigationBaseTab
import de.bornholdtlee.defaultproject.injection.IInjection
import de.bornholdtlee.defaultproject.injection.components.AppComponent

class MapFragment : MapBaseFragment<IMapView, MapPresenter>(), IMapView, IInjection, NavigationBaseTab {

    companion object {
        fun createInstance(): MapFragment {
            return MapFragment()
        }
    }

    override val mapViewId: Int = R.id.fragment_map_mapview

    override val layoutId: Int = R.layout.fragment_map

    override val navigationPosition: Int = TAB_MAP

    override fun inject(appComponent: AppComponent?) {
        appComponent?.inject(this)
    }

    override fun createPresenter(application: BaseApplication): MapPresenter {
        return MapPresenter(application, this)
    }
}