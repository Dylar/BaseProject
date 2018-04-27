package de.bornholdtlee.defaultproject.injection.modules

import dagger.Module
import dagger.Provides
import de.bornholdtlee.defaultproject.viewbuilder.ToastBuilder
import de.bornholdtlee.defaultproject.BaseApplication
import de.bornholdtlee.defaultproject.utils.*
import de.bornholdtlee.defaultproject.viewbuilder.DialogBuilder
import javax.inject.Singleton

@Module
class UtilsModule {

    @Provides
    @Singleton
    fun provideNetworkUtils(context: BaseApplication): NetworkUtils {
        return NetworkUtils(context)
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesUtils(context: BaseApplication): SharedPreferencesUtils {
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
    fun provideCommonUtils(): CommonUtils {
        return CommonUtils()
    }

    @Provides
    @Singleton
    fun provideDateUtils(): DateUtils {
        return DateUtils()
    }

}
