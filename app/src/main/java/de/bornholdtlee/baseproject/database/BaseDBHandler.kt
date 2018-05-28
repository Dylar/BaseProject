package de.bornholdtlee.baseproject.database

import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.injection.IInjection

abstract class BaseDBHandler(application: BaseApplication) {

    init {
        if (this is IInjection) {
            (this as IInjection).inject(application.appComponent)
        }
    }
}