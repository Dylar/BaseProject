package de.bornholdtlee.baseproject.injection.modules


import dagger.Module
import dagger.Provides
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.injection.ApplicationScope
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: BaseApplication) {

    @Provides
    @ApplicationScope
     fun provideApplication(): BaseApplication {
        return application
    }

}
