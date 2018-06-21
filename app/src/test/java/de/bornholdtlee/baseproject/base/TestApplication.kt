package de.bornholdtlee.baseproject.base

import de.bornholdtlee.baseproject.injection.modules.ApplicationModule
import de.bornholdtlee.baseproject.injection.modules.UtilsModule
import de.bornholdtlee.baseproject.utils.SharedPreferencesUtils
import org.mockito.Mockito

class TestApplication : BaseApplication() {

    override val appComponent: TestComponent
        get() = DaggerTestComponent.builder()
                .applicationModule(ApplicationModule(this))
                .utilsModule(provideUtilsModule())
                .build()

    private fun provideUtilsModule(): UtilsModule {
        return object: UtilsModule() {
            override fun provideSharedPreferencesUtils(context: BaseApplication): SharedPreferencesUtils {
                return Mockito.spy(SharedPreferencesUtils::class.java)
            }
        }
    }
}
