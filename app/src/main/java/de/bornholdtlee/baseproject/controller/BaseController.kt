package de.bornholdtlee.baseproject.controller

import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.injection.IInjection

abstract class BaseController(application: BaseApplication) {
    init {
        if (this is IInjection) {
            (this as IInjection).inject(application.appComponent)
        }
    }
}