package de.bornholdtlee.defaultproject.injection.modules

import dagger.Module
import dagger.Provides
import de.bornholdtlee.defaultproject.base.BaseApplication
import de.bornholdtlee.defaultproject.model.DefaultModel
import de.bornholdtlee.defaultproject.model.MyObjectBox
import io.objectbox.Box
import io.objectbox.BoxStore
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: BaseApplication): BoxStore {
        //        BoxStore boxStore = MyObjectBox.builder().androidContext(application).build();
        //        if (BuildConfig.DEBUG) {
        //            new AndroidObjectBrowser(boxStore).constructionsStart(application);
        //        }
        return MyObjectBox.builder().androidContext(application).build()
    }

    @Provides
    @Singleton
    fun provideDefaultBox(store: BoxStore): Box<DefaultModel> {
        return store.boxFor(DefaultModel::class.java)
    }

}
