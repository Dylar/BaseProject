package de.bornholdtlee.defaultproject.injection.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.bornholdtlee.defaultproject.DefaultApplication;
import de.bornholdtlee.defaultproject.model.DefaultModel;
import de.bornholdtlee.defaultproject.model.MyObjectBox;
import io.objectbox.Box;
import io.objectbox.BoxStore;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    public BoxStore provideDatabase(DefaultApplication application) {
        return MyObjectBox.builder().androidContext(application).build();
    }

    @Provides
    @Singleton
    public Box<DefaultModel> provideDefaultModelBox(BoxStore store) {
        return store.boxFor(DefaultModel.class);
    }
//
//    @Provides
//    @Singleton
//    public DeviceTypeDBHandler provideDeviceScanTypeDBHandler(DBSystelApplication application) {
//        return new DeviceTypeDBHandler(application);
//    }

}
