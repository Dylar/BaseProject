package de.bornholdtlee.defaultproject.injection.modules;


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
    @ApplicationScope
    public DefaultApplication provideApplication() {
        return application;
    }

}
