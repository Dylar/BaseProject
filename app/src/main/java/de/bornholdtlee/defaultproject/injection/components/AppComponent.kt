package de.bornholdtlee.defaultproject.injection.components

import dagger.Component
import de.bornholdtlee.defaultproject.base.BaseActivity
import de.bornholdtlee.defaultproject.base.BaseFragment
import de.bornholdtlee.defaultproject.controller.DefaultController
import de.bornholdtlee.defaultproject.injection.modules.*
import de.bornholdtlee.defaultproject.ui.main.MainFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, DatabaseModule::class, UtilsModule::class, ControllerModule::class])
interface AppComponent {
    fun inject(baseActivity: BaseActivity)

    fun inject(baseFragment: BaseFragment)
    fun inject(mainFragment: MainFragment)

    fun inject(defaultController: DefaultController)
}