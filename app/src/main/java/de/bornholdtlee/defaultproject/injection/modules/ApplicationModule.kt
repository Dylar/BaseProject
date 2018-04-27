package de.bornholdtlee.defaultproject.injection.modules


import dagger.Module
import dagger.Provides
import de.bornholdtlee.defaultproject.BaseApplication
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: BaseApplication) {

    @Provides
    @Singleton
     fun provideApplication(): BaseApplication {
        return application
    }

}
