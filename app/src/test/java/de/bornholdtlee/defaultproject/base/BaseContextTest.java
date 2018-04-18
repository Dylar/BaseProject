package de.bornholdtlee.defaultproject.base;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.robolectric.RuntimeEnvironment;

public abstract class BaseContextTest extends BaseTest {

    protected Context application;

    @Before
    public void setUp() throws Exception {
        application = RuntimeEnvironment.application;
    }

    public Context getContext() {
        return application;
    }

    @After
    public void tearDown() {
        // do nothing
    }
}