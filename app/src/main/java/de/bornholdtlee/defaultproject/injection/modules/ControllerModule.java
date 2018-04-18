package de.bornholdtlee.defaultproject.injection.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.bornholdtlee.defaultproject.DefaultApplication;
import de.bornholdtlee.defaultproject.controller.DefaultController;

@Module
public class ControllerModule {

    @Provides
    @Singleton
    public DefaultApplication provideAccountController(DefaultApplication application) {
        return new DefaultApplication(application);
    }

    @Provides
    @Singleton
    public DefaultController provideDefaultController(DefaultApplication application) {
        return new DefaultController(application);
    }
}
