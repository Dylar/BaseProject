package de.bornholdtlee.baseproject.base

import de.bornholdtlee.baseproject.BuildConfig
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(PowerMockRunner::class)
@PowerMockRunnerDelegate(RobolectricTestRunner::class)
@PowerMockIgnore("org.mockito.*", "org.robolectric.*", "android.*", "org.powermock.*")
@Config(sdk = [27], packageName = "de.bornholdtlee.defaultproject", constants = BuildConfig::class, application = TestApplication::class)
abstract class BaseTest() {

    val context: TestApplication = RuntimeEnvironment.application as TestApplication

    init {
        MockitoAnnotations.initMocks(this)
    }

    @Before
    @Throws(Exception::class)
    open fun setUp() {

        if (this is ITestInjection) {
            inject(context.appComponent)
        }
    }
}