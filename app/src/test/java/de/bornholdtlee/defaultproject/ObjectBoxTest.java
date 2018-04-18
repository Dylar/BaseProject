package de.bornholdtlee.defaultproject;


import org.junit.Test;

import java.util.List;

import javax.inject.Inject;

import de.bornholdtlee.defaultproject.base.injection.BaseInjectTest;
import de.bornholdtlee.defaultproject.base.injection.TestComponent;
import de.bornholdtlee.defaultproject.model.DefaultModel;
import io.objectbox.Box;

import static junit.framework.Assert.assertEquals;

public class ObjectBoxTest extends BaseInjectTest {

    @Inject
    Box<DefaultModel> defaultModelBox;

    @Override
    public void inject(TestComponent testComponent) {
        testComponent.inject(this);
    }

    @Test
    public void shouldInsertModel() {
        List<DefaultModel> all = defaultModelBox.getAll();
        assertEquals(0, all.size());

        DefaultModel model = new DefaultModel();
        model.setName("hallo");

        defaultModelBox.put(model);

        all = defaultModelBox.getAll();
        assertEquals(1, all.size());
        assertEquals("hallo", all.get(0).getName());
    }
}
