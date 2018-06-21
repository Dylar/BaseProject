package de.bornholdtlee.baseproject.injection.modules

import dagger.Module
import dagger.Provides
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.controller.DefaultController
import de.bornholdtlee.baseproject.controller.LessonController
import de.bornholdtlee.baseproject.controller.OrganizerController
import de.bornholdtlee.baseproject.injection.ApplicationScope

@Module
class ControllerModule {

    @Provides
    @ApplicationScope
    fun provideBaseController(baseApplication: BaseApplication): DefaultController {
        return DefaultController(baseApplication)
    }

    @Provides
    @ApplicationScope
    fun provideLessonController(baseApplication: BaseApplication): LessonController {
        return LessonController(baseApplication)
    }

    @Provides
    @ApplicationScope
    fun provideOrganizerController(baseApplication: BaseApplication): OrganizerController {
        return OrganizerController(baseApplication)
    }

}
