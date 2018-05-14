package de.bornholdtlee.defaultproject.base

import android.os.Bundle

abstract class MVPActivity<V : IBaseView, P : BasePresenter<V>> : BaseActivity() {

    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter(application as BaseApplication)
    }

    protected abstract fun createPresenter(application: BaseApplication): P
}
