package de.bornholdtlee.defaultproject.ui.map

import android.content.Context
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import de.bornholdtlee.defaultproject.R
import de.bornholdtlee.defaultproject.base.BaseApplication
import de.bornholdtlee.defaultproject.base.BaseFragment
import de.bornholdtlee.defaultproject.base.MapBaseFragment
import de.bornholdtlee.defaultproject.controller.DefaultController
import de.bornholdtlee.defaultproject.injection.IBind
import de.bornholdtlee.defaultproject.injection.IInjection
import de.bornholdtlee.defaultproject.injection.components.AppComponent
import de.bornholdtlee.defaultproject.model.DefaultModel
import de.bornholdtlee.defaultproject.utils.Logger
import de.bornholdtlee.defaultproject.utils.SharedPreferencesUtils
import io.objectbox.Box
import javax.inject.Inject

class MapFragment : MapBaseFragment<IMapView, MapPresenter>(), IMapView {

    override fun createPresenter(application: BaseApplication): MapPresenter {
        return MapPresenter(application, this)
    }

    override val mapViewId: Int = R.id.map_view

    companion object {
        fun createInstance(): MapFragment {
            return MapFragment()
        }
    }

    @Inject
    lateinit var defaultModelBox: Box<DefaultModel>

    @Inject
    lateinit var sharedPreferencesUtils: SharedPreferencesUtils

    @Inject
    lateinit var defaultController: DefaultController

    @BindView(R.id.fragment_main_welcome_text)
    lateinit var welcomeText: TextView

    override val layoutId: Int = R.layout.fragment_main

    private var counter: Int = 0

    override fun inject(appComponent: AppComponent?) {
        appComponent!!.inject(this)
    }

    override fun onResume() {
        super.onResume()

        makeTestApiCall()
        checkDB()
        readPreferences()
    }

    private fun readPreferences() {
        val test = sharedPreferencesUtils.getInt("TEST", -1)
        Logger.error("preferences: " + test)
    }

    private fun checkDB() {
        val all = defaultModelBox.all
        Logger.error("There are " + all.size + " models in the database.")
    }

    private fun makeTestApiCall() {
        defaultController.startDownload(this)
    }

    @OnClick(R.id.fragment_main_counter_button)
    fun incrementCounter() {
        welcomeText.text = (++counter).toString()
    }

    override fun onSuccess() {
        Logger.error("Test api call success")
    }

    override fun onClientError() {
        Logger.error("Test api call failure")
    }
}