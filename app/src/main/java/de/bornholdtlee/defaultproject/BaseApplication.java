package de.bornholdtlee.defaultproject;

import android.app.Application;

import com.facebook.stetho.Stetho;

import de.bornholdtlee.defaultproject.injection.components.AppComponent;
import de.bornholdtlee.defaultproject.injection.modules.ApplicationModule;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class BaseApplication extends Application {

    public static final String FONT_PATH = "fonts/Roboto-Light.ttf";

    private AppComponent appComponent;

    public BaseApplication(BaseApplication application) {

    }

    @Override
    public void onCreate() {
        super.onCreate();

        initStetho();
        initCalligraphy();
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerDefaultApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return appComponent;
    }

    private void initCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(FONT_PATH)
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    private void initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }
}