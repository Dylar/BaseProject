package de.bornholdtlee.defaultproject.base

import android.content.Context
import java.lang.ref.WeakReference
import de.bornholdtlee.defaultproject.injection.IInjection

abstract class BasePresenter<T : IBaseView>(application: BaseApplication, view: T) {

   private var viewRef: WeakReference<T> = WeakReference(view)

    val context: Context
        get() = getView().getContext()!!

    init {
        if (this is IInjection) {
            (this as IInjection).inject(application.appComponent)
        }
    }

    fun getView(): T {
        return viewRef.get()!!
    }

}
