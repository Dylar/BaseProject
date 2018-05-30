package de.bornholdtlee.baseproject.base.mvp

import android.os.Bundle
import de.bornholdtlee.baseproject.base.BaseActivity
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.base.BasePresenter
import de.bornholdtlee.baseproject.base.IBaseView

abstract class MVPActivity<V : IBaseView, P : BasePresenter<V>> : BaseActivity() {

    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter(application as BaseApplication)
    }

    protected abstract fun createPresenter(application: BaseApplication): P
}
