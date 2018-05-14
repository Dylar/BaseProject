package de.bornholdtlee.defaultproject.base

import android.content.Context

abstract class MVPFlowFragment<V : IBaseView, P : BasePresenter<V>> : BaseFragment() {

    lateinit var presenter: P

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter = (context as MVPActivity<V, P>).presenter
    }

}
