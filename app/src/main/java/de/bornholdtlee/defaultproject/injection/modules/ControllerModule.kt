package de.bornholdtlee.defaultproject.injection.modules

import dagger.Module
import dagger.Provides
import de.bornholdtlee.defaultproject.base.BaseApplication
import de.bornholdtlee.defaultproject.controller.DefaultController
import javax.inject.Singleton

@Module
class ControllerModule {

    @Provides
    @Singleton
    fun provideBaseController(baseApplication: BaseApplication): DefaultController {
        return DefaultController(baseApplication)
    }

}
