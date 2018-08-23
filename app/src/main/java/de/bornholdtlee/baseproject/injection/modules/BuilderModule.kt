package de.bornholdtlee.baseproject.injection.modules

import dagger.Module
import dagger.Provides
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.injection.ApplicationScope
import de.bornholdtlee.baseproject.utils.*
import de.bornholdtlee.baseproject.viewbuilder.DialogBuilder
import de.bornholdtlee.baseproject.viewbuilder.ToastBuilder

@Module
class BuilderModule {

    @Provides
    @ApplicationScope
    fun provideToastBuilder(): ToastBuilder {
        return ToastBuilder()
    }

    @Provides
    @ApplicationScope
    fun provideDialogBuilder(): DialogBuilder {
        return DialogBuilder()
    }

}
