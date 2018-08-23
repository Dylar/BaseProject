package de.bornholdtlee.baseproject.injection.modules

import dagger.Module
import dagger.Provides
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.injection.ApplicationScope
import de.bornholdtlee.baseproject.utils.*

@Module
class UtilsModule {

    @Provides
    @ApplicationScope
    fun provideNetworkUtils(application: BaseApplication): NetworkUtils {
        return NetworkUtils(application)
    }

    @Provides
    @ApplicationScope
    fun provideSharedPreferencesUtils(application: BaseApplication): SharedPreferencesUtils {
        return SharedPreferencesUtils(application)
    }

    @Provides
    @ApplicationScope
    fun provideUiUtils(application: BaseApplication): UiUtils {
        return UiUtils(application)
    }

    @Provides
    @ApplicationScope
    fun provideAndroidUtils(application: BaseApplication): AndroidUtils {
        return AndroidUtils(application)
    }

    @Provides
    @ApplicationScope
    fun provideStringUtils(application: BaseApplication): StringUtils {
        return StringUtils(application)
    }

    @Provides
    @ApplicationScope
    fun provideDateUtils(application: BaseApplication): DateUtils {
        return DateUtils(application)
    }

    @Provides
    @ApplicationScope
    fun providePermissionUtils(application: BaseApplication): PermissionUtils {
        return PermissionUtils(application)
    }

}
