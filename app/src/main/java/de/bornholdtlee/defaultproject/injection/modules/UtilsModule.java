package de.bornholdtlee.defaultproject.injection.modules;

import dagger.Module;
import dagger.Provides;
import de.bornholdtlee.defaultproject.DefaultApplication;
import de.bornholdtlee.defaultproject.utils.NetworkUtils;
import de.bornholdtlee.defaultproject.utils.SharedPreferencesUtils;
import de.bornholdtlee.defaultproject.utils.UiUtils;

@Module
public class UtilsModule {

    @Provides
    @ApplicationScope
    public SharedPreferencesUtils provideSharedPreferencesUtils(DefaultApplication defaultApplication) {
        return new SharedPreferencesUtils(defaultApplication);
    }

    @Provides
    @ApplicationScope
    public NetworkUtils provideNetworkUtils(DefaultApplication defaultApplication) {
        return new NetworkUtils(defaultApplication);
    }

    @Provides
    @ApplicationScope
    public UiUtils provideUiUtils() {
        return new UiUtils();
    }
}
