package de.bornholdtlee.defaultproject


import de.bornholdtlee.defaultproject.base.BaseTest
import de.bornholdtlee.defaultproject.base.ITestInjection
import org.junit.Test

import javax.inject.Inject

import de.bornholdtlee.defaultproject.base.TestComponent
import de.bornholdtlee.defaultproject.model.DefaultModel
import io.objectbox.Box

import junit.framework.Assert.assertEquals

class ObjectBoxTest : BaseTest(), ITestInjection {

    lateinit var defaultModelBox: Box<DefaultModel>
        @Inject set

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
