package de.bornholdtlee.baseproject.base.mvp

import android.os.Bundle
import android.view.View
import de.bornholdtlee.baseproject.base.*

abstract class MVPFragment<T : IBaseView, P : BasePresenter<T>> : BaseFragment() {

    lateinit var presenter: P

    var mvpActivity: MVPActivity<*, *>? = null
        get() = activity as MVPActivity<*, *>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = createPresenter(activity!!.application as BaseApplication)
    }

    open fun createPresenter(application: BaseApplication): P {
        return mvpActivity!!.presenter as P
    }
}
