package de.bornholdtlee.baseproject.utils

import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.injection.IInjection

abstract class BaseUtils(application: BaseApplication) {
    init {
        if (this is IInjection) {
            (this as IInjection).inject(application.appComponent)
        }
    }
}