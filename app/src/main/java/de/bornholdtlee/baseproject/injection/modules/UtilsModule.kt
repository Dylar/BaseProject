package de.bornholdtlee.baseproject.injection.modules

import dagger.Module
import dagger.Provides
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.injection.ApplicationScope
import de.bornholdtlee.baseproject.utils.*
import de.bornholdtlee.baseproject.viewbuilder.DialogBuilder
import de.bornholdtlee.baseproject.viewbuilder.ToastBuilder

@Module
open class UtilsModule {

    @Provides
    @ApplicationScope
    fun provideNetworkUtils(context: BaseApplication): NetworkUtils {
        return NetworkUtils(context)
    }

    @Provides
    @ApplicationScope
    open fun provideSharedPreferencesUtils(context: BaseApplication): SharedPreferencesUtils {
        return SharedPreferencesUtils(context)
    }

    @Provides
    @ApplicationScope
    fun provideToastBuilder(): ToastBuilder {
        return ToastBuilder()
    }

    @Provides
    @ApplicationScope
    fun provideUiUtils(): UiUtils {
        return UiUtils()
    }

    @Provides
    @ApplicationScope
    fun provideDialogBuilder(): DialogBuilder {
        return DialogBuilder()
    }

    @Provides
    @ApplicationScope
    fun provideAndroidUtils(): AndroidUtils {
        return AndroidUtils()
    }

    @Provides
    @ApplicationScope
    fun provideStringUtils(): StringUtils {
        return StringUtils()
    }

    @Provides
    @ApplicationScope
    fun provideDateUtils(): DateUtils {
        return DateUtils()
    }

}
