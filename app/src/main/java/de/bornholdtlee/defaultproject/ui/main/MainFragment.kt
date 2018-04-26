package de.bornholdtlee.defaultproject.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import de.bitb.astroskop.injection.IInjection
import de.bornholdtlee.defaultproject.base.BaseFragment
import de.bornholdtlee.defaultproject.controller.DefaultController
import de.bornholdtlee.defaultproject.injection.IBind
import de.bornholdtlee.defaultproject.injection.components.AppComponent
import de.bornholdtlee.defaultproject.model.DefaultModel
import de.bornholdtlee.defaultproject.utils.Logger
import de.bornholdtlee.defaultproject.utils.SharedPreferencesUtils
import io.objectbox.Box
import javax.inject.Inject

class MainFragment : BaseFragment(), DefaultController.Callback, IInjection, IBind {

    @Inject
    internal var defaultModelBox: Box<DefaultModel>? = null

    @Inject
    internal var sharedPreferencesUtils: SharedPreferencesUtils? = null

    @Inject
    internal var defaultController: DefaultController? = null

    @BindView(R.id.fragment_main_welcome_text)
    internal var welcomeText: TextView? = null


    override val layoutId: Int
        get() = R.layout.fragment_main

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
        val test = sharedPreferencesUtils!!.getInt("TEST", -1)
        Logger.error("preferences: " + test)
    }

    private fun checkDB() {
        val all = defaultModelBox!!.all
        Logger.error("There are " + all.size + " models in the database.")
    }

    private fun makeTestApiCall() {
        defaultController!!.startDownload(this)
    }

    @OnClick(R.id.fragment_main_counter_button)
    fun incrementCounter() {
        welcomeText!!.text = (++counter).toString()
    }

    override fun onSuccess() {
        Logger.error("Test api call success")
    }

    override fun onClientError() {
        Logger.error("Test api call failure")
    }
}