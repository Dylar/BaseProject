package de.bornholdtlee.defaultproject.controller

import de.bornholdtlee.defaultproject.base.BaseApplication
import de.bornholdtlee.defaultproject.injection.IInjection

class BaseController(application: BaseApplication) {
    init {
        if (this is IInjection) {
            (this as IInjection).inject(application.appComponent)
        }
    }
}
