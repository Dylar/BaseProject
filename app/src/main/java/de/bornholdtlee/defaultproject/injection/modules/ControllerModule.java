package de.bornholdtlee.defaultproject.injection.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.bornholdtlee.defaultproject.DefaultApplication;

@Module
public class ControllerModule {

    @Provides
    @Singleton
    public DefaultApplication provideAccountController(DefaultApplication application) {
        return new DefaultApplication(application);
    }
}
