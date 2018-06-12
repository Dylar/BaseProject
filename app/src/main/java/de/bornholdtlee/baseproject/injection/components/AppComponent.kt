package de.bornholdtlee.baseproject.injection.components

import dagger.Component
import de.bornholdtlee.baseproject.base.BaseActivity
import de.bornholdtlee.baseproject.base.BaseFragment
import de.bornholdtlee.baseproject.controller.DefaultController
import de.bornholdtlee.baseproject.controller.LessonController
import de.bornholdtlee.baseproject.controller.OrganizerController
import de.bornholdtlee.baseproject.injection.ApplicationScope
import de.bornholdtlee.baseproject.injection.modules.*
import de.bornholdtlee.baseproject.ui.creation.CreateLessonPresenter
import de.bornholdtlee.baseproject.ui.main.MainFragment
import de.bornholdtlee.baseproject.ui.map.MapFragment
import de.bornholdtlee.baseproject.ui.map.MapPresenter

@ApplicationScope
@Component(modules = [ApplicationModule::class, NetworkModule::class, DatabaseModule::class, UtilsModule::class, ControllerModule::class])
interface AppComponent {
    fun inject(baseActivity: BaseActivity)

    fun inject(baseFragment: BaseFragment)
    fun inject(mainFragment: MainFragment)
    fun inject(baseFragment: MapFragment)

    fun inject(defaultController: DefaultController)
    fun inject(lessonController: LessonController)
    fun inject(organizerController: OrganizerController)

    fun inject(mapPresenter: MapPresenter)
    fun inject(createLessonPresenter: CreateLessonPresenter)
}