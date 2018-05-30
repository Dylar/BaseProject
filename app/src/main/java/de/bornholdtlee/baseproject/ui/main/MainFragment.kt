package de.bornholdtlee.baseproject.ui.main

import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.TAB_HOME
import de.bornholdtlee.baseproject.base.BaseFragment
import de.bornholdtlee.baseproject.base.navigation.NavigationBaseTab
import de.bornholdtlee.baseproject.controller.DefaultController
import de.bornholdtlee.baseproject.injection.IBind
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.injection.components.AppComponent
import de.bornholdtlee.baseproject.model.DefaultModel
import de.bornholdtlee.baseproject.utils.Logger
import de.bornholdtlee.baseproject.utils.SharedPreferencesUtils
import io.objectbox.Box
import javax.inject.Inject

class MainFragment : BaseFragment(), DefaultController.Callback, IInjection, IBind, NavigationBaseTab {

    companion object {
        fun createInstance(): MainFragment {
            return MainFragment()
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

    override val navigationPosition: Int = TAB_HOME

    override val layoutId: Int = R.layout.fragment_main

    private var counter: Int = 0

    override fun inject(appComponent: AppComponent?) {
        appComponent?.inject(this)
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
//        val all = defaultModelBox.all
//        Logger.error("There are " + all.size + " models in the database.")
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