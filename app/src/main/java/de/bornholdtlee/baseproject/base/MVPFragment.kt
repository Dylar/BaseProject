package de.bornholdtlee.baseproject.base

import android.os.Bundle
import android.view.View

abstract class MVPFragment<T : IBaseView, P : BasePresenter<T>> : BaseFragment() {

    lateinit var presenter: P

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = createPresenter(activity!!.application as BaseApplication)
    }

    protected abstract fun createPresenter(application: BaseApplication): P
}
