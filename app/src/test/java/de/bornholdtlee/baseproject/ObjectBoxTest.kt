package de.bornholdtlee.baseproject


import de.bornholdtlee.baseproject.base.BaseTest
import de.bornholdtlee.baseproject.base.ITestInjection
import de.bornholdtlee.baseproject.base.TestComponent
import io.objectbox.Box
import junit.framework.Assert.assertEquals
import org.junit.Test
import javax.inject.Inject

class ObjectBoxTest : BaseTest(), ITestInjection {

    @Inject
    lateinit var defaultModelBox: Box<DefaultModel>

    override fun inject(testComponent: TestComponent) {
        testComponent.inject(this)
    }

    @Test
    fun shouldInsertModel() {
        var all = defaultModelBox.all
        assertEquals(0, all.size)

        val model = DefaultModel()
        model.name = "hallo"

        defaultModelBox.put(model)

        all = defaultModelBox.all
        assertEquals(1, all.size)
        assertEquals("hallo", all[0].name)
    }
}
