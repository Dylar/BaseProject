package de.bornholdtlee.defaultproject.base;

import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import de.bornholdtlee.defaultproject.BuildConfig;
import de.bornholdtlee.defaultproject.base.injection.TestApplication;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(RobolectricTestRunner.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*", "org.powermock.*"})
@Config(sdk = 27, packageName = "de.bornholdtlee.defaultproject", constants = BuildConfig.class, application = TestApplication.class)
public abstract class BaseTest {

    public BaseTest() {
        MockitoAnnotations.initMocks(this);
    }
}