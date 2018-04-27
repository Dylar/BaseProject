package de.bornholdtlee.defaultproject

import android.app.Application

import com.facebook.stetho.Stetho

import de.bornholdtlee.defaultproject.injection.components.AppComponent
import de.bornholdtlee.defaultproject.injection.components.DaggerAppComponent
import de.bornholdtlee.defaultproject.injection.modules.ApplicationModule
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

open class BaseApplication : Application() {

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        initStetho()
        initCalligraphy()
    }

    open fun getAppComponent(): AppComponent {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .applicationModule(ApplicationModule(this))
                    .build()
        }
        return appComponent
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

    companion object {

        val FONT_PATH = "fonts/Roboto-Light.ttf"
    }
}