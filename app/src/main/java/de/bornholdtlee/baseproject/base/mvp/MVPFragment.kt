package de.bornholdtlee.baseproject.base.mvp

import android.os.Bundle
import android.view.View
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.base.BaseFragment
import de.bornholdtlee.baseproject.base.BasePresenter
import de.bornholdtlee.baseproject.base.IBaseView

abstract class MVPFragment<T : IBaseView, P : BasePresenter<T>> : BaseFragment() {

    lateinit var presenter: P

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = createPresenter(activity!!.application as BaseApplication)
    }

    protected abstract fun createPresenter(application: BaseApplication): P
}
