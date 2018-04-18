package de.bornholdtlee.defaultproject.injection.modules;

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
    @ApplicationScope
    public BoxStore provideDatabase(DefaultApplication application) {
        return MyObjectBox.builder().androidContext(application).build();
    }

    @Provides
    @ApplicationScope
    public Box<DefaultModel> provideDefaultModel(BoxStore store) {
        return store.boxFor(DefaultModel.class);
    }
}
