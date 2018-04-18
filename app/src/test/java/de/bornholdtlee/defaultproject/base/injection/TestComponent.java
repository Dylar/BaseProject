package de.bornholdtlee.defaultproject.base.injection;

import dagger.Component;
import de.bornholdtlee.defaultproject.ObjectBoxTest;
import de.bornholdtlee.defaultproject.injection.components.DefaultApplicationComponent;
import de.bornholdtlee.defaultproject.injection.modules.ApplicationModule;
import de.bornholdtlee.defaultproject.injection.modules.ApplicationScope;
import de.bornholdtlee.defaultproject.injection.modules.ControllerModule;
import de.bornholdtlee.defaultproject.injection.modules.DatabaseModule;
import de.bornholdtlee.defaultproject.injection.modules.NetworkModule;
import de.bornholdtlee.defaultproject.injection.modules.UtilsModule;

@ApplicationScope
@Component(modules = {ApplicationModule.class, NetworkModule.class, DatabaseModule.class, UtilsModule.class, ControllerModule.class})
public interface TestComponent extends DefaultApplicationComponent {

    void inject(ObjectBoxTest objectBoxTest);
}
