package de.bornholdtlee.defaultproject.base.injection;

import de.bornholdtlee.defaultproject.base.BaseApplication;
import de.bornholdtlee.defaultproject.injection.modules.ApplicationModule;

public class TestApplication extends BaseApplication {

    private TestComponent testComponent;

    @Override
    public TestComponent getAppComponent() {
        return testComponent;
    }

    public DaggerTestComponent.Builder initComponent() {
        return DaggerTestComponent.builder()
                .applicationModule(new ApplicationModule(this));
    }

    public void setTestComponent(TestComponent testComponent) {
        this.testComponent = testComponent;
    }
}
