package de.bornholdtlee.defaultproject.injection.components

import dagger.Component
import de.bornholdtlee.defaultproject.controller.DefaultController
import de.bornholdtlee.defaultproject.injection.modules.ApplicationModule
import de.bornholdtlee.defaultproject.injection.modules.ControllerModule
import de.bornholdtlee.defaultproject.injection.modules.DatabaseModule
import de.bornholdtlee.defaultproject.injection.modules.NetworkModule
import de.bornholdtlee.defaultproject.injection.modules.UtilsModule
import de.bornholdtlee.defaultproject.ui.main.MainFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, DatabaseModule::class, UtilsModule::class, ControllerModule::class])
interface DefaultApplicationComponent {

    fun inject(mainFragment: MainFragment)

    fun inject(defaultController: DefaultController)
}