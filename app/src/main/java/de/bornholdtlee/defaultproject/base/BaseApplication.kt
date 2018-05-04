package de.bornholdtlee.defaultproject.base

import android.app.Application

import com.facebook.stetho.Stetho
import de.bornholdtlee.defaultproject.BuildConfig
import de.bornholdtlee.defaultproject.R

import de.bornholdtlee.defaultproject.injection.components.AppComponent
import de.bornholdtlee.defaultproject.injection.components.DaggerAppComponent
import de.bornholdtlee.defaultproject.injection.modules.ApplicationModule
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

}