package de.bornholdtlee.defaultproject.base

import javax.inject.Singleton

import dagger.Component
import de.bornholdtlee.defaultproject.ObjectBoxTest
import de.bornholdtlee.defaultproject.injection.components.AppComponent
import de.bornholdtlee.defaultproject.injection.modules.ApplicationModule
import de.bornholdtlee.defaultproject.injection.modules.ControllerModule
import de.bornholdtlee.defaultproject.injection.modules.DatabaseModule
import de.bornholdtlee.defaultproject.injection.modules.NetworkModule
import de.bornholdtlee.defaultproject.injection.modules.UtilsModule

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetworkModule::class, DatabaseModule::class, UtilsModule::class, ControllerModule::class))
interface TestComponent : AppComponent {

    fun inject(objectBoxTest: ObjectBoxTest)
}
