package de.bornholdtlee.baseproject.base.mvp

import android.content.Context
import de.bornholdtlee.baseproject.base.BaseFragment
import de.bornholdtlee.baseproject.base.BasePresenter
import de.bornholdtlee.baseproject.base.IBaseView

abstract class MVPFlowFragment<V : IBaseView, P : BasePresenter<V>> : BaseFragment() {

    lateinit var presenter: P

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter = (context as MVPActivity<V, P>).presenter
    }

}
