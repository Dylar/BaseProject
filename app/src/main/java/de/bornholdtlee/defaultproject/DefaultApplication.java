package de.bornholdtlee.defaultproject;

import android.app.Application;

import com.facebook.stetho.Stetho;

import de.bornholdtlee.defaultproject.injection.components.DefaultApplicationComponent;
import de.bornholdtlee.defaultproject.injection.modules.ApplicationModule;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class DefaultApplication extends Application {

    public static final String FONT_PATH = "fonts/Roboto-Light.ttf";

    private DefaultApplicationComponent defaultApplicationComponent;

    public DefaultApplication(DefaultApplication application) {

    }

    @Override
    public void onCreate() {
        super.onCreate();

        initStetho();
        initCalligraphy();
    }

    public DefaultApplicationComponent getDefaultApplicationComponent() {
        if (defaultApplicationComponent == null) {
            defaultApplicationComponent = DaggerDefaultApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return defaultApplicationComponent;
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