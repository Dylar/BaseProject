package de.bornholdtlee.defaultproject.injection.modules

import dagger.Module
import dagger.Provides
import de.bornholdtlee.defaultproject.base.BaseApplication
import de.bornholdtlee.defaultproject.utils.*
import de.bornholdtlee.defaultproject.viewbuilder.DialogBuilder
import de.bornholdtlee.defaultproject.viewbuilder.ToastBuilder
import javax.inject.Singleton

@Module
open class UtilsModule {

    @Provides
    @Singleton
    fun provideNetworkUtils(context: BaseApplication): NetworkUtils {
        return NetworkUtils(context)
    }

    @Provides
    @Singleton
    open fun provideSharedPreferencesUtils(context: BaseApplication): SharedPreferencesUtils {
        return SharedPreferencesUtils(context)
    }

    @Provides
    @Singleton
    fun provideToastBuilder(): ToastBuilder {
        return ToastBuilder()
    }

    @Provides
    @Singleton
    fun provideUiUtils(): UiUtils {
        return UiUtils()
    }

    @Provides
    @Singleton
    fun provideDialogBuilder(): DialogBuilder {
        return DialogBuilder()
    }

    @Provides
    @Singleton
    fun provideAndroidUtils(): AndroidUtils {
        return AndroidUtils()
    }

    @Provides
    @Singleton
    fun provideStringUtils(): StringUtils {
        return StringUtils()
    }

    @Provides
    @Singleton
    fun provideDateUtils(): DateUtils {
        return DateUtils()
    }

}
