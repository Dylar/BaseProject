package de.bornholdtlee.defaultproject.injection.modules;

import dagger.Module;
import dagger.Provides;
import de.bornholdtlee.defaultproject.DefaultApplication;
import de.bornholdtlee.defaultproject.controller.DefaultController;

@Module
public class ControllerModule {

    @Provides
    @ApplicationScope
    public DefaultController provideDefaultController(DefaultApplication defaultApplication) {
        return new DefaultController(defaultApplication);
    }
}
