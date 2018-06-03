package de.bornholdtlee.baseproject.injection.modules

import dagger.Module
import dagger.Provides
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.controller.DefaultController
import de.bornholdtlee.baseproject.controller.LessonController
import javax.inject.Singleton

@Module
class ControllerModule {

    @Provides
    @Singleton
    fun provideBaseController(baseApplication: BaseApplication): DefaultController {
        return DefaultController(baseApplication)
    }

    @Provides
    @Singleton
    fun provideLessonController(baseApplication: BaseApplication): LessonController {
        return LessonController(baseApplication)
    }

}
