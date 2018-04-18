package de.bornholdtlee.defaultproject.base.injection;

import de.bornholdtlee.defaultproject.DefaultApplication;
import de.bornholdtlee.defaultproject.injection.modules.ApplicationModule;

public class TestApplication extends DefaultApplication {

    private TestComponent testComponent;

    @Override
    public TestComponent getDefaultApplicationComponent() {
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
