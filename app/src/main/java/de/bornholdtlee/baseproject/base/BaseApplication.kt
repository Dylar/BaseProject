package de.bornholdtlee.baseproject.base

import android.app.Application

import com.facebook.stetho.Stetho
import de.bornholdtlee.baseproject.BuildConfig
import de.bornholdtlee.baseproject.R

import de.bornholdtlee.baseproject.injection.components.AppComponent
import de.bornholdtlee.baseproject.injection.components.DaggerAppComponent
import de.bornholdtlee.baseproject.injection.modules.ApplicationModule
import de.bornholdtlee.baseproject.utils.Logger
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

open class BaseApplication : Application() {

    companion object {
        const val FONT_PATH = "fonts/Roboto-Light.ttf"
    }

    open val appComponent: AppComponent
        get() = DaggerAppComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

    override fun onCreate() {
        super.onCreate()

        initStetho()
        initCalligraphy()
    }

    private fun initCalligraphy() {
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath(FONT_PATH)
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        Logger.error("YEA TERMINTATE")
    }

}