package de.bornholdtlee.baseproject.base

import javax.inject.Singleton

import dagger.Component
import de.bornholdtlee.baseproject.ObjectBoxTest
import de.bornholdtlee.baseproject.injection.components.AppComponent
import de.bornholdtlee.baseproject.injection.modules.ApplicationModule
import de.bornholdtlee.baseproject.injection.modules.ControllerModule
import de.bornholdtlee.baseproject.injection.modules.DatabaseModule
import de.bornholdtlee.baseproject.injection.modules.NetworkModule
import de.bornholdtlee.baseproject.injection.modules.UtilsModule

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetworkModule::class, DatabaseModule::class, UtilsModule::class, ControllerModule::class))
interface TestComponent : AppComponent {

    fun inject(objectBoxTest: ObjectBoxTest)
}
