package de.bornholdtlee.defaultproject.injection.modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.bornholdtlee.defaultproject.DefaultApplication;

@Module
public class ApplicationModule {

    private final DefaultApplication application;

    public ApplicationModule(DefaultApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public DefaultApplication provideApplication() {
        return application;
    }

}
