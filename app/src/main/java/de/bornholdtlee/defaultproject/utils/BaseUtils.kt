package de.bornholdtlee.defaultproject.utils


import de.bornholdtlee.defaultproject.base.BaseApplication
import de.bornholdtlee.defaultproject.injection.IInjection

class BaseUtils(application: BaseApplication) {
    init {
        if (this is IInjection) {
            (this as IInjection).inject(application.appComponent)
        }
    }
}
