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
import de.bornholdtlee.baseproject.examples.AnyController
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.injection.components.AppComponent
import javax.inject.Inject


fun createInstance(): BrownbagFragment {
    return BrownbagFragment()
}

class BrownbagFragment : BaseFragment(), IInjection, NavigationBaseTab {

//    companion object {
//    }

    @Inject
    lateinit var anyController: AnyController

    @BindView(R.id.fragment_main_welcome_text)
    lateinit var welcomeText: TextView

    override val navigationPosition: Int = TAB_HOME
    override val layoutId: Int = R.layout.fragment_main

    var counter: Int = 0

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

    }

    @OnClick(R.id.fragment_brownbag_previous_button)
    fun doPrevious() {
        counter--
        anyController.doIt(counter)
    }

    @OnClick(R.id.fragment_brownbag_next_button)
    fun doNext() {
        counter++
        anyController.doIt(counter.also {  })
    }

}