package de.bornholdtlee.defaultproject.database

import de.bornholdtlee.defaultproject.base.BaseApplication
import de.bornholdtlee.defaultproject.injection.IInjection

abstract class BaseDBHandler(application: BaseApplication) {

    init {
        if (this is IInjection) {
            (this as IInjection).inject(application.appComponent)
        }
    }
}