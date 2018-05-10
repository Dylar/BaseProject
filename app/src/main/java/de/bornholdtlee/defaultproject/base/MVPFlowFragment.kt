package de.bornholdtlee.defaultproject.base

import android.content.Context

abstract class MVPFlowFragment<T : IBaseView, P : BasePresenter<T>> : BaseFragment() {

    lateinit var presenter: P

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter = (context as MVPActivity<T, P>).presenter
    }

}
