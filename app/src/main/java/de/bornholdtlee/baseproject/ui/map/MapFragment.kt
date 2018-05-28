package de.bornholdtlee.baseproject.ui.map

import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.TAB_MAP
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.base.MapBaseFragment
import de.bornholdtlee.baseproject.base.NavigationBaseTab
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.injection.components.AppComponent

class MapFragment : MapBaseFragment<IMapView, MapPresenter>(), IMapView, IInjection, NavigationBaseTab {

    companion object {
        fun createInstance(): MapFragment {
            return MapFragment()
        }
    }

    override val mapViewId: Int = R.id.map_view

    override val layoutId: Int = R.layout.fragment_map

    override val navigationPosition: Int = TAB_MAP

    override fun inject(appComponent: AppComponent?) {
        appComponent?.inject(this)
    }

    override fun createPresenter(application: BaseApplication): MapPresenter {
        return MapPresenter(application, this)
    }
}