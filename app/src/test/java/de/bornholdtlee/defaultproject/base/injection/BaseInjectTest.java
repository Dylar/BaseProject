package de.bornholdtlee.defaultproject.base.injection;


import org.junit.Before;

import de.bornholdtlee.defaultproject.DefaultApplication;
import de.bornholdtlee.defaultproject.base.BaseContextTest;
import de.bornholdtlee.defaultproject.injection.modules.UtilsModule;
import de.bornholdtlee.defaultproject.utils.SharedPreferencesUtils;

import static org.mockito.Mockito.spy;


public abstract class BaseInjectTest extends BaseContextTest implements ITestInjection {

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();

        TestApplication application = (TestApplication) getContext();

        initComponent(application);
        inject(application.getDefaultApplicationComponent());
    }

    private void initComponent(TestApplication application) {
        DaggerTestComponent.Builder builder = application.initComponent();
        builder.utilsModule(provideSharedPreferencesUtils());
        application.setTestComponent(builder.build());
    }

    private UtilsModule provideSharedPreferencesUtils() {
        return new UtilsModule() {
            @Override
            public SharedPreferencesUtils provideSharedPreferencesUtils(DefaultApplication defaultApplication) {
                return spy(SharedPreferencesUtils.class);
            }
        };
    }
}
