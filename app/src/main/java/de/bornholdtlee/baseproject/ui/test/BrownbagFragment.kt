package de.bornholdtlee.baseproject.ui.test

import android.os.Bundle
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.TAB_HOME
import de.bornholdtlee.baseproject.base.BaseFragment
import de.bornholdtlee.baseproject.base.navigation.NavigationBaseTab
import de.bornholdtlee.baseproject.examples.TestController
import de.bornholdtlee.baseproject.examples.TestController.Companion.MAX_TEST
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.injection.components.AppComponent
import javax.inject.Inject


class BrownbagFragment : BaseFragment(), IInjection, NavigationBaseTab {

    companion object {
        fun createInstance(): BrownbagFragment {
            return BrownbagFragment()
        }
    }

    @Inject
    lateinit var testController: TestController

    @BindView(R.id.fragment_brownbag_test)
    lateinit var counterTV: TextView

    override val navigationPosition: Int = TAB_HOME
    override val layoutId: Int = R.layout.fragment_brownbag

    var counter: Int = 0

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateText()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun updateText() {
        counterTV.text = testController.getTestName(counter)
    }

    @OnClick(R.id.fragment_brownbag_previous_button)
    fun doPrevious() {
        counter--
        if (counter < 0) counter = 0
        updateText()

    }

    @OnClick(R.id.fragment_brownbag_doit_button)
    fun doIt() {
        testController.doIt(counter)
    }

    @OnClick(R.id.fragment_brownbag_next_button)
    fun doNext() {
        counter++
        if (counter > MAX_TEST) counter = MAX_TEST
        updateText()
    }

}