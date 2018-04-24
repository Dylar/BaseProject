package de.bornholdtlee.defaultproject.injection.components

import dagger.Component
import de.bitb.astroskop.injection.modules.ApplicationModule
import de.bitb.astroskop.injection.modules.ControllerModule
import de.bitb.astroskop.injection.modules.DatabaseModule
import de.bitb.astroskop.injection.modules.NetworkModule
import de.bornholdtlee.defaultproject.base.BaseActivity
import de.bornholdtlee.defaultproject.base.BaseFragment
import de.bornholdtlee.defaultproject.controller.DefaultController
import de.bornholdtlee.defaultproject.injection.modules.UtilsModule
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